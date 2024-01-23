package com.salt.server.Account.service;

import com.salt.server.Account.api.dto.AccountDto;
import com.salt.server.Account.mapper.AccountMapper;
import com.salt.server.Account.model.*;
import com.salt.server.Account.repository.AccountRepository;
import com.salt.server.Account.repository.SocialRepository;
import com.salt.server.Account.repository.UserDetailRepository;
import com.salt.server.github.Github;
import com.salt.server.github.GithubRepository;
import com.salt.server.github.Project;
import com.salt.server.github.ProjectRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserDetailRepository userDetailRepository;
    private final SocialRepository socialRepository;
    private final GithubRepository githubRepository;
    private final ProjectRepository projectRepository;

    public AccountService(AccountRepository accountRepository, UserDetailRepository userDetailRepository, SocialRepository socialRepository, GithubRepository githubRepository, ProjectRepository projectRepository) {
        this.accountRepository = accountRepository;
        this.userDetailRepository = userDetailRepository;
        this.socialRepository = socialRepository;
        this.githubRepository = githubRepository;
        this.projectRepository = projectRepository;
    }

    public List<Account> getAllAccount() {
        return accountRepository.findAll();
    }

    public AccountDto.AccountResponse getAccountById(String id) {
        Account account = accountRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new NoSuchElementException("Account not found"));

        return AccountMapper.toAccountResponse(account);
    }

    public AccountDto.AccountResponse createAccount(AccountDto.AccountRequest request) {
        Account account = new Account();
        account.setUsername(request.username());
        Account saveAccount = accountRepository.save(account);

        UserDetail userDetail = new UserDetail();
        userDetail.setAccount(saveAccount);
        userDetail.setName(request.name());
        userDetail.setIntroduction(request.standoutIntro());
        userDetail.setBootcamp(request.bootcamp());
        userDetail.setNationality(request.backgroudInformations().nationalities());
        UserDetail saveUserDetail = userDetailRepository.save(userDetail);

        Social social = new Social();
        social.setUserDetail(saveUserDetail);
        social.setLinkedInUrl(request.linkedinUrl());
        social.setCodewarsUrl(request.codewarsUrl());
        Social saveSocial = socialRepository.save(social);

        Github github = new Github();
        github.setSocial(saveSocial);
        github.setUrl(request.githubUrl());
        github.setPictureUrl(request.githubUrl());
        Github saveGithub = githubRepository.save(github);

        for (String project : request.selectedProjectUrls()) {
            Project newProject = new Project();
            newProject.setGithub(saveGithub);
            newProject.setUrl(project);
            projectRepository.save(newProject);
        }

        return AccountMapper.toAccountResponse(saveAccount);
    }
}
