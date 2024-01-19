package com.salt.server.Account;

import com.salt.server.Account.model.Account;
import com.salt.server.Account.repository.AccountRepository;
import com.salt.server.Account.repository.UserDetailRepository;
import org.springframework.stereotype.Service;

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

    private Account getAccountById(String id) {
        return accountRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new NoSuchElementException("Account not found"));
    }

    private Account addAccount(Account account) {
        return accountRepository.save(account);
    }
}
