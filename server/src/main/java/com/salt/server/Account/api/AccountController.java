package com.salt.server.Account.api;

import com.salt.server.Account.api.dto.AccountDto;
import com.salt.server.Account.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<AccountDto.Response> getAllAccount() {
        return accountService.getAllAccount();
    }

    @GetMapping("/{accountId}")
    public AccountDto.Response getAccountById(@PathVariable UUID accountId) {
        return accountService.getAccountById(accountId);
    }

    @PostMapping
    public AccountDto.Response createAccount(@RequestBody AccountDto.Request request) {
        return accountService.createAccount(request);
    }
}
