package com.salt.server.Account.api;

import com.salt.server.Account.AccountService;
import com.salt.server.Account.api.dto.AccountRequest;
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
    public Account getAccountById(@PathVariable String accountId) {
        return accountService.getAccountById(accountId);
    }

//    @PostMapping
//    public Account createAccount(@RequestBody AccountRequest accountRequest) {
//        return accountService.createDeveloperAccount(accountRequest);
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccount(@RequestParam("file") MultipartFile file) {
         accountService.createDeveloperAccountCSV(file);
    }
}
