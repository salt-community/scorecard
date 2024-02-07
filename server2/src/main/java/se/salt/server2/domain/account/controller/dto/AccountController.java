package se.salt.server2.domain.account.controller.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import se.salt.server2.domain.account.service.AccountService;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("api/v2/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    @ResponseStatus(CREATED)
    public AccountResponse createAccount(@RequestBody AccountRequest accountRequest) {
        return accountService.createAccount(accountRequest);
    }
}