package com.salt.server.Account.service;

import com.salt.server.Account.api.dto.AccountDto;
import com.salt.server.Account.mapper.AccountMapper;
import com.salt.server.Account.model.*;
import com.salt.server.Account.repository.*;
import com.salt.server.github.GithubService;
import com.salt.server.github.model.Github;
import com.salt.server.github.repository.GithubRepository;
import com.salt.server.github.model.Project;
import com.salt.server.github.repository.ProjectRepository;
import com.salt.server.score.ScoreService;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserDetailRepository userDetailRepository;
    private final SocialRepository socialRepository;
    private final GithubRepository githubRepository;
    private final AcademicRepository academicRepository;


    public AccountService(AccountRepository accountRepository, UserDetailRepository userDetailRepository, SocialRepository socialRepository, GithubRepository githubRepository,
                          AcademicRepository academicRepository) {
        this.accountRepository = accountRepository;
        this.userDetailRepository = userDetailRepository;
        this.socialRepository = socialRepository;
        this.githubRepository = githubRepository;
        this.academicRepository = academicRepository;
    }

    public List<AccountDto.Response> getAllAccount() {
        return accountRepository.findAll().stream().map(AccountMapper::toAccountResponse).toList();
    }

    public List<AccountDto.CoreTeamResponse> getAllCoreTeam() {
        return accountRepository.findAllByRole(Role.core).stream().map(account -> new AccountDto.CoreTeamResponse(account.getId(), account.getUserDetail().getName(), account.getEmail(), account.getRole().toString())).toList();
    }

    public AccountDto.Response getAccountById(UUID id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Account not found"));
        return AccountMapper.toAccountResponse(account);
    }

    public AccountDto.Response createAccount(AccountDto.Request request) {
        Account account = new Account();
        account.setEmail(request.email());
        account.setRole(request.role());

        UserDetail userDetail = UserDetail.builder()
                .name(request.name())
                .bootcamp("")
                .phoneNumber("")
                .build();
        Github github =  new Github();
        github.setUrl("https://github.com/null");
        Academic academic = new Academic();
        academic.setDegree("");
        academic.setMajor("");
        academic.setSchool("");
        Social social = Social.builder()
                .githubId(github)
                .codewarsUrl("https://www.codewars.com/users/null")
                .linkedInUrl("https://www.linkedin.com/in/null")
                .build();
        github.setSocial(social);
        userDetail.setSocial(social);
        userDetail.setAcademic(academic);
        academic.setUserDetail(userDetail);
        social.setUserDetail(userDetail);
        academicRepository.save(academic);
        githubRepository.save(github);
        socialRepository.save(social);
        account.setUserDetail(userDetail);
        userDetailRepository.save(userDetail);
        return AccountMapper.toAccountResponse(accountRepository.save(account));
    }

    public Account getDeveloperById(UUID id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Developer not found"));
    }

    public AccountDto.CoreTeamResponse getAccountByEmail(String email) {
        Account account = accountRepository.findByEmailAndRole(email, Role.core)
                .orElseThrow(() -> new NoSuchElementException("Account not found"));
        return new AccountDto.CoreTeamResponse(
                account.getId(),
                account.getUserDetail().getName(),
                account.getEmail(),
                account.getRole().toString()
        );
    }

    public void deleteAccountById(UUID id) {
        accountRepository.deleteById(id);
    }
}
