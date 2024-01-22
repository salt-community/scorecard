package com.salt.server.Account.api;

import com.salt.server.Account.service.AccountService;
import com.salt.server.Account.api.dto.AccountResponse;
import com.salt.server.Account.model.Account;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<Account> getAllAccount() {
        return accountService.getAllAccount();
    }

    @GetMapping("/{accountId}")
    public AccountResponse getAccountById(@PathVariable String accountId) {
        return accountService.getAccountById(accountId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccount(@RequestParam("file") MultipartFile file) {
         accountService.createDeveloperAccountCSV(file);
    }
}
