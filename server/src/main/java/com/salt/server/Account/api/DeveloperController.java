package com.salt.server.Account.api;

import com.salt.server.Account.api.dto.AccountDto;
import com.salt.server.Account.api.dto.DeveloperDto;
import com.salt.server.Account.model.Account;
import com.salt.server.Account.service.DeveloperService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/developers")
public class DeveloperController {

    private final DeveloperService developerService;

    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @GetMapping
    public List<DeveloperDto.ShowcaseResponse> getAllDevelopers() {
        return developerService.getAllDeveloper();
    }

    @GetMapping("/{accountId}")
    public AccountDto.AccountResponse getDeveloperById(@PathVariable UUID accountId) {
        return accountService.getAccountById(accountId);
    }
}
