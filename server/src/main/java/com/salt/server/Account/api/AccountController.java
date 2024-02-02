package com.salt.server.Account.api;

import com.salt.server.Account.api.dto.AccountDto;
import com.salt.server.Account.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/accounts")
@CrossOrigin
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

    @GetMapping("/email/{email}")
    public AccountDto.CoreTeamResponse getAccountByEmail(@PathVariable String email) {
        return accountService.getAccountByEmail(email);
    }
    @PostMapping
    public AccountDto.Response createAccount(@RequestBody AccountDto.Request request) {
        return accountService.createAccount(request);
    }

    @GetMapping("/core-team")
    public List<AccountDto.CoreTeamResponse> getCoreTeam() {
        return accountService.getAllCoreTeam();
    }

    @DeleteMapping("/{accountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount (@PathVariable UUID accountId) {
        accountService.deleteAccountById(accountId);
    }
}
