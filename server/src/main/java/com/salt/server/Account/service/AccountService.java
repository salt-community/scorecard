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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserDetailRepository userDetailRepository;
    private final SocialRepository socialRepository;
    private final LanguageRepository languageRepository;
    private final SkillRepository skillRepository;
    private final AcademicRepository academicRepository;
    private final NationalityRepository nationalityRepository;
    private final ScoreService scoreService;
    private final GithubService githubService;


    public AccountService(AccountRepository accountRepository,
                          UserDetailRepository userDetailRepository,
                          SocialRepository socialRepository,
                          GithubService githubService,
                          LanguageRepository languageRepository,
                          SkillRepository skillRepository,
                          AcademicRepository academicRepository,
                          NationalityRepository nationalityRepository,
                          ScoreService scoreService) {
        this.accountRepository = accountRepository;
        this.userDetailRepository = userDetailRepository;
        this.socialRepository = socialRepository;
        this.githubService = githubService;
        this.languageRepository = languageRepository;
        this.skillRepository = skillRepository;
        this.academicRepository = academicRepository;
        this.nationalityRepository = nationalityRepository;
        this.scoreService = scoreService;
    }

    public List<AccountDto.ListAccountsDto> getAllAccount() {
        return accountRepository.findAll().stream().map(AccountMapper::toListAccountDto).toList();
    }

    public AccountDto.AccountResponse getAccountById(UUID id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Account not found"));
        List<AccountDto.RadarGraph> radarGraphs = scoreService.calculateRadarGraph(account);
        return AccountMapper.toAccountResponse(account, radarGraphs);
    }

    public AccountDto.AccountResponse createAccount(AccountDto.AccountRequest request) {
        Account account = new Account();
        account.setEmail(request.email());
        Account saveAccount = accountRepository.save(account);

        UserDetail userDetail = createUserDetail(request, saveAccount);
        saveAccount.setUserDetail(userDetail);
        createAcademic(request, userDetail);
        createNationality(request, userDetail);
        createLanguage(request, userDetail);
        createSkill(request,userDetail);
        Social social = createSocial(request, userDetail);
        Github github = githubService.createGithub(request, social);
        githubService.createProject(request, github);

        List<AccountDto.RadarGraph> radarGraphs = scoreService.calculateRadarGraph(account);

        return AccountMapper.toAccountResponse(account, radarGraphs);
    }

    private UserDetail createUserDetail(AccountDto.AccountRequest request, Account account) {
        UserDetail userDetail = new UserDetail();
        userDetail.setAccount(account);
        userDetail.setName(request.name());
        userDetail.setIntroduction(request.standoutIntro());
        userDetail.setBootcamp(request.bootcamp());
        return userDetailRepository.save(userDetail);
    }

    private void createAcademic(AccountDto.AccountRequest request, UserDetail userDetail) {
        Academic academicDetail = request.backgroundInformation().academic();

        Academic academic = Academic.builder()
                .userDetail(userDetail)
                .degree(academicDetail.getDegree())
                .major(academicDetail.getMajor())
                .startDate(academicDetail.getStartDate())
                .endDate(academicDetail.getEndDate())
                .school(academicDetail.getSchool())
                .build();

        userDetail.setAcademic(academic);
        academicRepository.save(academic);
    }

    private Social createSocial(AccountDto.AccountRequest request, UserDetail userDetail) {
        Social social = new Social();
        social.setUserDetail(userDetail);
        social.setLinkedInUrl(request.linkedinUsername());
        social.setCodewarsUrl(request.codewarsUsername());
        userDetail.setSocial(social);
        return socialRepository.save(social);
    }

    private void createNationality(AccountDto.AccountRequest request, UserDetail userDetail) {
        for (var nationality : request.backgroundInformation().nationalities()) {
            Nationality newNationality = new Nationality();
            newNationality.setUserDetail(userDetail);
            newNationality.setNationality(nationality);
            userDetail.addNationality(newNationality);
            nationalityRepository.save(newNationality);
        }
    }

    private void createLanguage(AccountDto.AccountRequest request, UserDetail userDetail) {
        for (var language : request.backgroundInformation().spokenLanguages().entrySet()) {
            Language newLanguage = new Language();
            newLanguage.setUserDetail(userDetail);
            newLanguage.setLanguage(language.getKey());
            newLanguage.setFluency(language.getValue().toString());
            userDetail.addLanguage(newLanguage);
            languageRepository.save(newLanguage);
        }
    }

    private void createSkill(AccountDto.AccountRequest request, UserDetail userDetail) {
        for (var skill : request.backgroundInformation().skills()) {
            Skill newSkill = new Skill();
            newSkill.setUserDetail(userDetail);
            newSkill.setSkill(skill);
            userDetail.addSkill(newSkill);
            skillRepository.save(newSkill);
        }
    }


}
