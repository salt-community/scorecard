package se.salt.server2.domain.account.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.salt.server2.domain.account.controller.dto.AccountRequest;
import se.salt.server2.domain.account.controller.dto.AccountResponse;
import se.salt.server2.domain.account.controller.dto.AccountResponses;
import se.salt.server2.domain.account.mapper.AccountMapper;
import se.salt.server2.domain.account.models.AccountEntity;
import se.salt.server2.domain.account.repository.AccountRepository;
import se.salt.server2.exception.AccountDoesNotExistException;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountResponse createAccount(AccountRequest accountRequest) {
        return accountMapper.mapToResponse(accountRepository.save(accountMapper.mapToEntity(accountRequest)));
    }

    public AccountResponse getAccountById(UUID accountId) {
        return accountMapper.mapToResponse(
                accountRepository.findById(accountId).orElseThrow(() -> new AccountDoesNotExistException(accountId)));
    }

    public AccountResponses getAllAccounts() {
        return accountMapper.mapToAccountResponses(accountRepository.findAll());
    }

    public AccountResponses getAllNonDeveloperAccounts() {
        return accountMapper.mapToAccountResponses(accountRepository.findAllNonDevelopers());
    }

    public AccountResponse updateAccountById(UUID accountId, AccountRequest accountRequest) {
        AccountEntity account = accountRepository.findById(accountId).orElseThrow(() -> new AccountDoesNotExistException(accountId));
        account.setEmailAddress(accountRequest.emailAddress());
        return accountMapper.mapToResponse(accountRepository.save(account));
    }

    public void deleteAccountById(UUID accountId) {
        accountRepository.deleteById(accountId);
    }
}