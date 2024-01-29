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
    private final UserDetailRepository userDetailRepository;

    public AccountService(AccountRepository accountRepository, UserDetailRepository userDetailRepository) {
        this.accountRepository = accountRepository;
        this.userDetailRepository = userDetailRepository;
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
        UserDetail userDetail = UserDetail.builder()
                .name(request.name())
                .phoneNumber(request.phoneNumber())
                .build();
        account.setUserDetail(userDetail);
        userDetailRepository.save(userDetail);
        return AccountMapper.toAccountResponse(accountRepository.save(account));
    }

    public Account getDeveloperById(UUID id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Developer not found"));
    }
}
