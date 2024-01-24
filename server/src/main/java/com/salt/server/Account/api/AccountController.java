package com.salt.server.Account.api;

import com.salt.server.Account.api.dto.AccountDto;
import com.salt.server.Account.service.AccountService;
import com.salt.server.Account.model.Account;
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
    public List<AccountDto.ListAccountsDto> getAllAccount() {
        return accountService.getAllAccount();
    }

    @GetMapping("/{accountId}")
    public AccountDto.AccountResponse getAccountById(@PathVariable UUID accountId) {
        return accountService.getAccountById(accountId);
    }

    @PostMapping
    public AccountDto.AccountResponseTest createAccount(@RequestBody AccountDto.AccountRequest request) {
        return accountService.createAccount(request);
    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public void createAccount(@RequestParam("file") MultipartFile file) {
//         accountService.createDeveloperAccountCSV(file);
//    }
}
