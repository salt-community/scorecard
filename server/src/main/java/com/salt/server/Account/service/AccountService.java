package com.salt.server.Account.service;

import com.salt.server.Account.api.dto.AccountDto;
import com.salt.server.Account.mapper.AccountMapper;
import com.salt.server.Account.model.*;
import com.salt.server.Account.repository.*;
import com.salt.server.assignment.model.Type;
import com.salt.server.github.Github;
import com.salt.server.github.GithubRepository;
import com.salt.server.github.Project;
import com.salt.server.github.ProjectRepository;
import com.salt.server.score.Score;
import com.salt.server.score.ScoreService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private final LanguageRepository languageRepository;
    private final SkillRepository skillRepository;
    private final AcademicRepository academicRepository;
    private final NationalityRepository nationalityRepository;
    private final ScoreService scoreService;

    public AccountService(AccountRepository accountRepository, UserDetailRepository userDetailRepository, SocialRepository socialRepository, GithubRepository githubRepository, ProjectRepository projectRepository, LanguageRepository languageRepository, SkillRepository skillRepository, AcademicRepository academicRepository, NationalityRepository nationalityRepository, ScoreService scoreService) {
        this.accountRepository = accountRepository;
        this.userDetailRepository = userDetailRepository;
        this.socialRepository = socialRepository;
        this.githubRepository = githubRepository;
        this.projectRepository = projectRepository;
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

    public AccountDto.AccountResponseTest createAccount(AccountDto.AccountRequest request) {
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
        Github github = createGithub(request, social);
        createProject(request, github);

        List<AccountDto.RadarGraph> radarGraphs = scoreService.calculateRadarGraph(account);
        List<AccountDto.Scores> scoresList = new ArrayList<>();

        for(var type: Type.values()) {

            AccountDto.Scores scores = new AccountDto.Scores(
                    type.toString(),
                    account.getScores()!=null?
                    account.getScores().stream()
                            .filter(score -> score.getAssignment().getType().equals(type) )
                            .collect(Collectors.toMap(score -> score.getAssignment().getName(), Score::getScore))
                            : null
            );
            scoresList.add(scores);
        }

        Github git = githubRepository.findById(github.getId())
                .orElseThrow(() -> new NoSuchElementException("Account not found"));


        return new AccountDto.AccountResponseTest(
                saveAccount.getId(),
                saveAccount.getEmail(),
                userDetail.getName(),
                userDetail.getIntroduction(),
                userDetail.getBootcamp().name(),
                github.getUrl(),
                github.getUrl().substring(github.getUrl().lastIndexOf("/")+1),
                github.getPictureUrl(),
                social.getLinkedInUrl(),
                social.getCodewarsUrl(),
                radarGraphs,
                scoresList,
                git.getProjects().stream()
                        .map(data -> new AccountDto.ProjectDto(
                                data.getUrl().substring(data.getUrl().lastIndexOf("/")+1),
                                data.getUrl(),
                                new AccountDto.GithubData(
                                        data.getCommit(),
                                        data.getIssue(),
                                        data.getDuration(),
                                        data.getPerformance(),
                                        data.getTestCoverage()
                                ))).toList(),
                new AccountDto.BackgroundInformation(
                        account.getUserDetail().getNationality().stream().map(Nationality::getNationality).toList(),
                        account.getUserDetail().getLanguages().stream().collect(Collectors.toMap(Language::getLanguage,Language::getFluency)),
                        account.getUserDetail().getAcademic(),
                        account.getUserDetail().getSkills().stream().map(Skill::getSkill).toList()
                )
        );
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
        Academic academic = new Academic();
        academic.setUserDetail(userDetail);
        academic.setDegree(request.backgroundInformation().academic().getDegree());
        academic.setMajor(request.backgroundInformation().academic().getMajor());
        academic.setStartDate(request.backgroundInformation().academic().getStartDate());
        academic.setEndDate(request.backgroundInformation().academic().getEndDate());
        academic.setSchool(request.backgroundInformation().academic().getSchool());
        academicRepository.save(academic);
    }

    private Social createSocial(AccountDto.AccountRequest request, UserDetail userDetail) {
        Social social = new Social();
        social.setUserDetail(userDetail);
        social.setLinkedInUrl(request.linkedinUsername());
        social.setCodewarsUrl(request.codewarsUsername());
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

    private Github createGithub(AccountDto.AccountRequest request, Social social) {
        Github github = new Github();
        github.setSocial(social);
        github.setUrl(request.githubUsername());
        github.setPictureUrl(request.githubUsername());
        return githubRepository.save(github);
    }

    private void createProject(AccountDto.AccountRequest request, Github github) {
        for (String project : request.selectedProjects()) {
            Project newProject = new Project();
            newProject.setGithub(github);
            newProject.setUrl(project);
            github.addProject(newProject);
            projectRepository.save(newProject);
        }
    }
}
