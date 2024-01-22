package com.salt.server.Account;

import com.salt.server.Account.api.dto.AccountRequest;
import com.salt.server.Account.api.dto.AccountResponse;
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

    public AccountResponse getAccountById(String id) {
        Account account = accountRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new NoSuchElementException("Account not found"));
        UserDetail userDetail = userDetailRepository.findByAccount_Id(UUID.fromString(id))
                .orElseThrow(() -> new NoSuchElementException("userDetail not found"));
        return new AccountResponse(account.getId(), userDetail, userDetail.getSocial(), userDetail.getSocial().getGithubId());
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

                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                Account account = createAccount(data);
                UserDetail userDetail = createUserDetail(account, data);
                Social social = createSocial(userDetail, data);
                createGithub(social, data);
            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Github createGithub(Social social, String[] newUser) {
        Github github = new Github();
        github.setSocial(social);
        github.setUrl(newUser[4]);
        github.setPictureUrl(newUser[4]);

        return githubRepository.save(github);

    }

    private Social createSocial(UserDetail userDetail, String[] newUser) {
        Social social = new Social();
        social.setUserDetail(userDetail);
        social.setLinkedInUrl(newUser[5]);
        social.setCodewarsUrl(newUser[6]);

        return socialRepository.save(social);
    }

    private UserDetail createUserDetail(Account account, String[] newUser) {
        UserDetail userDetail = new UserDetail();
        userDetail.setAccount(account);
        userDetail.setBootcamp(newUser[1]);
        userDetail.setName(newUser[2]);
        userDetail.setNationality(newUser[7]);
        userDetail.setLanguages(newUser[8]);
        userDetail.setEducation(newUser[9]);
        userDetail.setSkills(newUser[10]);

        return userDetailRepository.save(userDetail);
    }

    private Account createAccount(String[] newUser) {
        Account newAccount = new Account();
        newAccount.setUsername(newUser[3]);

        return accountRepository.save(newAccount);
    }
}
