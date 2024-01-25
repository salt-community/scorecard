package com.salt.server.Account.service;

import com.salt.server.Account.api.dto.DeveloperDto;
import com.salt.server.Account.mapper.DeveloperMapper;
import com.salt.server.Account.model.Role;
import com.salt.server.Account.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeveloperService {

    private final AccountRepository accountRepository;

    public DeveloperService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<DeveloperDto.ShowcaseResponse> getAllDeveloper() {
        return accountRepository.findAllByRole(Role.developer).stream()
                .map(DeveloperMapper::toShowcaseResponse).toList();
    }
}
