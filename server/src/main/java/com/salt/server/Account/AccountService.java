package com.salt.server.Account;

import com.salt.server.Account.api.dto.AccountRequest;
import com.salt.server.Account.model.Account;
import com.salt.server.Account.model.Bootcamp;
import com.salt.server.Account.model.Role;
import com.salt.server.Account.model.UserDetail;
import com.salt.server.Account.repository.AccountRepository;
import com.salt.server.Account.repository.UserDetailRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserDetailRepository userDetailRepository;

    public AccountService(AccountRepository accountRepository, UserDetailRepository userDetailRepository) {
        this.accountRepository = accountRepository;
        this.userDetailRepository = userDetailRepository;
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
                Account newAccount = new Account();
                newAccount.setUsername(data[3]);
                newAccount.setRole(Role.ROLE_DEVELOPER);
                Account saveAccount = accountRepository.save(newAccount);

                UserDetail userDetail = new UserDetail();
                userDetail.setAccount(saveAccount);
                switch (data[1]) {
                    case "java":
                        userDetail.setBootcamp(Bootcamp.JAVA);
                        break;
                    case "javascript":
                        userDetail.setBootcamp(Bootcamp.JAVASCRIPT);
                        break;
                    default: userDetail.setBootcamp(Bootcamp.DOTNET);
                }
                userDetail.setName(data[2]);

                saveAccount.setUserDetail(userDetailRepository.save(userDetail));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
