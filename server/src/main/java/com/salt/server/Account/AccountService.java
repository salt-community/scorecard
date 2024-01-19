package com.salt.server.Account;

import com.salt.server.Account.api.dto.AccountRequest;
import com.salt.server.Account.model.*;
import com.salt.server.Account.repository.AccountRepository;
import com.salt.server.Account.repository.SocialRepository;
import com.salt.server.Account.repository.UserDetailRepository;
import com.salt.server.github.Github;
import com.salt.server.github.GithubRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserDetailRepository userDetailRepository;
    private final SocialRepository socialRepository;
    private final GithubRepository githubRepository;

    public AccountService(AccountRepository accountRepository, UserDetailRepository userDetailRepository, SocialRepository socialRepository, GithubRepository githubRepository) {
        this.accountRepository = accountRepository;
        this.userDetailRepository = userDetailRepository;
        this.socialRepository = socialRepository;
        this.githubRepository = githubRepository;
    }

    public List<Account> getAllAccount() {
        return accountRepository.findAll();
    }

    public Account getAccountById(String id) {
        return accountRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new NoSuchElementException("Account not found"));
    }

    public Account createDeveloperAccount(AccountRequest accountRequest) {
        Account newAccount = new Account();
        newAccount.setUsername(accountRequest.username());
        newAccount.setRole(Role.ROLE_DEVELOPER);
        Account saveAccount = accountRepository.save(newAccount);

        UserDetail userDetail = new UserDetail();
        userDetail.setAccount(saveAccount);
        userDetail.setBootcamp(Bootcamp.JAVASCRIPT);
        userDetail.setName(accountRequest.name());

        saveAccount.setUserDetail(userDetailRepository.save(userDetail));
        return saveAccount;
    }

    public void createDeveloperAccountCSV(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            int index = 1;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] data = line.split(",");

                Account account = createAccount(data);
                UserDetail userDetail = createUserDetail(account, data);
                Social social = createSocial(userDetail, data);
                Github github = createGithub(social, data);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Github createGithub(Social social, String[] newUser) {
        Github github = new Github();
        github.setUrl(String.format("https://github.com/%s", newUser[4]));
        github.setPictureUrl(String.format("https://github.com/%s.png", newUser[4]));
        Github savedGithub = githubRepository.save(github);
        social.setGithubId(github);
        return savedGithub;
    }

    private Social createSocial(UserDetail userDetail, String[] newUser) {
        Social social = new Social();
        social.setLinkedInUrl(String.format("https://www.linkedin.com/in/%s", newUser[5]));
        social.setCodewarsUrl(String.format("https://www.codewars.com/users/%s", newUser[6]));
        Social savedSocial = socialRepository.save(social);

        userDetail.setSocial(social);
        return savedSocial;
    }

    private UserDetail createUserDetail(Account account, String[] newUser) {
        UserDetail userDetail = new UserDetail();
        userDetail.setAccount(account);
        switch (newUser[1]) {
            case "java":
                userDetail.setBootcamp(Bootcamp.JAVA);
                break;
            case "javascript":
                userDetail.setBootcamp(Bootcamp.JAVASCRIPT);
                break;
            default: userDetail.setBootcamp(Bootcamp.DOTNET);
        }
        userDetail.setName(newUser[2]);
        UserDetail saveUserDetail = userDetailRepository.save(userDetail);
        account.setUserDetail(saveUserDetail);
        return saveUserDetail;
    }

    private Account createAccount(String[] newUser) {
        Account newAccount = new Account();
        newAccount.setUsername(newUser[3]);
        newAccount.setRole(Role.ROLE_DEVELOPER);
        return accountRepository.save(newAccount);
    }
}
