package com.salt.server.Account.service;

import com.salt.server.Account.api.dto.AccountDto;
import com.salt.server.Account.mapper.AccountMapper;
import com.salt.server.Account.model.*;
import com.salt.server.Account.repository.*;
import com.salt.server.github.GithubService;
import com.salt.server.github.model.Github;
import com.salt.server.github.repository.GithubRepository;
import com.salt.server.github.model.Project;
import com.salt.server.github.repository.ProjectRepository;
import com.salt.server.score.ScoreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository
    ) {
        this.accountRepository = accountRepository;
    }

    public List<AccountDto.Response> getAllAccount() {
        return accountRepository.findAll().stream().map(AccountMapper::toAccountResponse).toList();
    }

    public AccountDto.Response getAccountById(UUID id) {
         Account account = accountRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Account not found"));
        return AccountMapper.toAccountResponse(account);
    }

    public AccountDto.Response createAccount(AccountDto.Request request) {
        Account account = new Account();
        account.setEmail(request.email());
        account.setRole(request.role());
        return AccountMapper.toAccountResponse(accountRepository.save(account));
    }
}
