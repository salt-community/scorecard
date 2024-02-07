package se.salt.server2.domain.account.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.salt.server2.domain.account.controller.dto.AccountRequest;
import se.salt.server2.domain.account.controller.dto.AccountResponse;
import se.salt.server2.domain.account.mapper.AccountMapper;
import se.salt.server2.domain.account.repository.AccountRepository;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountResponse createAccount(AccountRequest accountRequest) {
        return accountMapper.mapToResponse(accountRepository.save(accountMapper.mapToEntity(accountRequest)));
    }
}