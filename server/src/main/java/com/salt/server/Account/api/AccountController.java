package com.salt.server.Account.api;

import com.salt.server.Account.api.dto.AccountDto;
import com.salt.server.Account.service.AccountService;
import com.salt.server.Account.model.Account;
import org.springframework.web.bind.annotation.*;

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
    public AccountDto.AccountResponse getAccountById(@PathVariable String accountId) {
        return accountService.getAccountById(accountId);
    }

    @PostMapping
    public AccountDto.AccountResponse createAccount(@RequestBody AccountDto.AccountRequest request) {
        return accountService.createAccount(request);
    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public void createAccount(@RequestParam("file") MultipartFile file) {
//         accountService.createDeveloperAccountCSV(file);
//    }
}
