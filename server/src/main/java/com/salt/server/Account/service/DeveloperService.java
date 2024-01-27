package com.salt.server.Account.service;

import com.salt.server.Account.api.dto.DeveloperDto;
import com.salt.server.Account.mapper.DeveloperMapper;
import com.salt.server.Account.model.*;
import com.salt.server.Account.repository.*;
import com.salt.server.github.GithubService;
import com.salt.server.github.model.Github;
import com.salt.server.score.ScoreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class DeveloperService {

    private final AccountRepository accountRepository;
    private final UserDetailRepository userDetailRepository;
    private final SocialRepository socialRepository;
    private final LanguageRepository languageRepository;
    private final SkillRepository skillRepository;
    private final AcademicRepository academicRepository;
    private final NationalityRepository nationalityRepository;
    private final ScoreService scoreService;
    private final GithubService githubService;

    public DeveloperService(AccountRepository accountRepository,
                            UserDetailRepository userDetailRepository,
                            SocialRepository socialRepository,
                            LanguageRepository languageRepository,
                            SkillRepository skillRepository,
                            AcademicRepository academicRepository,
                            NationalityRepository nationalityRepository,
                            ScoreService scoreService,
                            GithubService githubService) {
        this.accountRepository = accountRepository;
        this.userDetailRepository = userDetailRepository;
        this.socialRepository = socialRepository;
        this.languageRepository = languageRepository;
        this.skillRepository = skillRepository;
        this.academicRepository = academicRepository;
        this.nationalityRepository = nationalityRepository;
        this.scoreService = scoreService;
        this.githubService = githubService;
    }

    public List<DeveloperDto.ShowcaseResponse> getAllDeveloper() {
        return accountRepository.findAllByRoleNot(Role.core).stream()
                .map(DeveloperMapper::toShowcaseResponse).toList();
    }

    public List<DeveloperDto.AdminResponse> adminGetAllDevelopers() {
        return accountRepository.findAllByRoleNot(Role.core).stream()
                .map(account -> new DeveloperDto.AdminResponse(
                        account.getId().toString(),
                        account.getUserDetail().getName(),
                        account.getEmail(),
                        account.getUserDetail().getPhoneNumber(),
                        account.getRole().toString()
                )).toList();
    }

    public Account getDeveloperById(UUID id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Developer not found"));
    }

    public DeveloperDto.Response getDeveloperByIdtoResponse(UUID id) {
        Account account = getDeveloperById(id);
        List<DeveloperDto.RadarGraph> radarGraphs = scoreService.calculateRadarGraph(account);
        return DeveloperMapper.toDeveloperResponse(account, radarGraphs);
    }

    @Transactional
    public DeveloperDto.Response createDeveloper(DeveloperDto.Request request) {
        Account developerAccount = new Account();
        developerAccount.setEmail(request.email());
        developerAccount.setRole(request.role());
        Account saveAccount = accountRepository.save(developerAccount);

        UserDetail userDetail = createUserDetail(request, saveAccount);
        saveAccount.setUserDetail(userDetail);
        createAcademic(request, userDetail);
        createNationality(request, userDetail);
        createLanguage(request, userDetail);
        createSkill(request, userDetail);
        Social social = createSocial(request, userDetail);
        Github github = githubService.createGithub(request, social);
        githubService.createProject(request, github);

        List<DeveloperDto.RadarGraph> radarGraphs = scoreService.calculateRadarGraph(developerAccount);

        return DeveloperMapper.toDeveloperResponse(developerAccount, radarGraphs);
    }

    private UserDetail createUserDetail(DeveloperDto.Request request, Account account) {
        UserDetail userDetail = UserDetail.builder()
                .account(account)
                .name(request.name())
                .introduction(request.standoutIntro())
                .phoneNumber(request.phoneNumber())
                .bootcamp(request.bootcamp())
                .build();

        return userDetailRepository.save(userDetail);
    }

    private void createAcademic(DeveloperDto.Request request, UserDetail userDetail) {
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

    private Social createSocial(DeveloperDto.Request request, UserDetail userDetail) {
        Social social = Social.builder()
                .userDetail(userDetail)
                .linkedInUrl(request.linkedinUsername())
                .codewarsUrl(request.codewarsUsername())
                .build();

        userDetail.setSocial(social);
        return socialRepository.save(social);
    }

    private void createNationality(DeveloperDto.Request request, UserDetail userDetail) {
        for (var nationality : request.backgroundInformation().nationalities()) {
            Nationality newNationality = Nationality.builder()
                    .userDetail(userDetail)
                    .nationality(nationality)
                    .build();

            userDetail.addNationality(newNationality);
            nationalityRepository.save(newNationality);
        }
    }

    private void createLanguage(DeveloperDto.Request request, UserDetail userDetail) {
        for (var language : request.backgroundInformation().spokenLanguages().entrySet()) {
            Language newLanguage = Language.builder()
                    .userDetail(userDetail)
                    .language(language.getKey())
                    .fluency(language.getValue().toString())
                    .build();

            userDetail.addLanguage(newLanguage);
            languageRepository.save(newLanguage);
        }
    }

    private void createSkill(DeveloperDto.Request request, UserDetail userDetail) {
        for (var skill : request.backgroundInformation().skills()) {
            Skill newSkill = Skill.builder()
                    .userDetail(userDetail)
                    .skill(skill)
                    .build();

            userDetail.addSkill(newSkill);
            skillRepository.save(newSkill);
        }
    }
}
