package se.salt.server2.domain.account.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import se.salt.server2.domain.account.controller.dto.AccountRequest;
import se.salt.server2.domain.account.controller.dto.AccountResponse;
import se.salt.server2.domain.account.controller.dto.AccountResponses;
import se.salt.server2.domain.account.service.AccountService;

import java.util.UUID;

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

    @GetMapping(
            path = "/{accountId}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public AccountResponse getSpecificAccount(@PathVariable("accountId") UUID id) {
        return accountService.getAccountById(id);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public AccountResponses getAccounts() {
        return accountService.getAllAccounts();
    }
}