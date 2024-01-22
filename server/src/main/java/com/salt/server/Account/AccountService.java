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

//    public Account createDeveloperAccount(AccountRequest accountRequest) {
//        Account newAccount = new Account();
//        newAccount.setUsername(accountRequest.username());
//        newAccount.setRole(Role.ROLE_DEVELOPER);
//        Account saveAccount = accountRepository.save(newAccount);
//
//        UserDetail userDetail = new UserDetail();
//        userDetail.setAccount(saveAccount);
//        userDetail.setName(accountRequest.name());
//
//        saveAccount.setUserDetail(userDetailRepository.save(userDetail));
//        return saveAccount;
//    }

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

                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                Account account = createAccount(data);
                UserDetail userDetail = createUserDetail(account, data);
                Social social = createSocial(userDetail, data);
                Github github = createGithub(social, data);
            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Github createGithub(Social social, String[] newUser) {
        Github github = new Github();
        github.setUrl(newUser[4]);
        github.setPictureUrl(newUser[4]);
        Github savedGithub = githubRepository.save(github);
        social.setGithubId(github);
        return savedGithub;
    }

    private Social createSocial(UserDetail userDetail, String[] newUser) {
        Social social = new Social();
        social.setLinkedInUrl( newUser[5]);
        social.setCodewarsUrl(newUser[6]);
        Social savedSocial = socialRepository.save(social);

        userDetail.setSocial(social);
        return savedSocial;
    }

    private UserDetail createUserDetail(Account account, String[] newUser) {
        UserDetail userDetail = new UserDetail();
        userDetail.setAccount(account);
        userDetail.setBootcamp(newUser[1]);
        userDetail.setName(newUser[2]);
        userDetail.setEducation(newUser[9]);
        userDetail.setNationality(newUser[7]);
        userDetail.setSkills(newUser[10]);
        userDetail.setLanguages(newUser[8]);
        UserDetail saveUserDetail = userDetailRepository.save(userDetail);
        account.setUserDetail(saveUserDetail);
        return saveUserDetail;
    }

    private Account createAccount(String[] newUser) {
        Account newAccount = new Account();
        newAccount.setUsername(newUser[3]);
        return accountRepository.save(newAccount);
    }
}
