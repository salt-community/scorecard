package com.salt.server.Account.service;

import com.salt.server.Account.api.dto.DeveloperDto;
import com.salt.server.Account.mapper.DeveloperMapper;
import com.salt.server.Account.model.*;
import com.salt.server.Account.repository.*;
import com.salt.server.assignment.model.Type;
import com.salt.server.github.GithubService;
import com.salt.server.github.model.Github;
import com.salt.server.github.model.Project;
import com.salt.server.score.Score;
import com.salt.server.score.ScoreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

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

    public List<DeveloperDto.ScoreboardResponse> adminGetAllSaltieScoreboard() {
        List<Account> accounts = accountRepository.findAllByRole(Role.saltie);
        List<DeveloperDto.ScoreboardResponse> scoreboardResponses = new ArrayList<>();
        for (Account account : accounts) {
            List<DeveloperDto.RadarGraph> radarGraphs = scoreService.calculateRadarGraph(account);
            scoreboardResponses.add(new DeveloperDto.ScoreboardResponse(
                    account.getId().toString(),
                    account.getUserDetail().getName(),
                    radarGraphs
            ));
        }
        return scoreboardResponses;
    }

    public DeveloperDto.DeveloperScoreboardResponse adminGetDeveloperScoreboard(UUID id) {
        Account account = getDeveloperById(id);
        List<DeveloperDto.RadarGraph> radarGraphs = scoreService.calculateRadarGraph(account);
        List<DeveloperDto.ScoreDetail> scoreScoreboards = getScoreboardScore(account);
        List<DeveloperDto.Average> averages = new ArrayList<>();
        for (Type type : Type.values()) {
            List<Score> filteredScores = account.getScores().stream()
                    .filter(score -> score.getAssignment().getType().equals(type))
                    .toList();
            if (filteredScores.isEmpty()) {
                averages.add(new DeveloperDto.Average(type.toString(), 0));
            } else {
                averages.add(new DeveloperDto.Average(type.toString(), filteredScores.stream()
                        .mapToInt(Score::getScore)
                        .sum() / filteredScores.size()));
            }
        }
        averages.add(new DeveloperDto.Average("total", averages.stream()
                .mapToInt(DeveloperDto.Average::average)
                .sum() / 3));

        return new DeveloperDto.DeveloperScoreboardResponse(
                account.getId().toString(),
                account.getUserDetail().getName(),
                account.getUserDetail().getBootcamp().toString(),
                account.getUserDetail().getSocial().getGithubId().getPictureUrl(),
                account.getUserDetail().getSocial().getGithubId().getUrl(),
                account.getUserDetail().getSocial().getLinkedInUrl(),
                radarGraphs,
                scoreScoreboards,
                averages
        );
    }

    public List<DeveloperDto.ScoreDetail> getScoreboardScore(Account account) {

        return account.getScores().stream()
                .map(score -> new DeveloperDto.ScoreDetail(
                        score.getId().toString(),
                        score.getAssignment().getType().toString(),
                        score.getAssignment().getName(),
                        score.getScore(),
                        score.getDescription()))
                .toList();

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

    public DeveloperDto.AdminDeveloper adminGetAdminDeveloperById(UUID id) {
        Account account = getDeveloperById(id);
        UserDetail userDetail = userDetailRepository.findByAccount(account)
                .orElseThrow(() -> new NoSuchElementException("Developer not found"));
        Social social = socialRepository.findByUserDetail(userDetail);
        List<Skill> skills = skillRepository.findAllByUserDetail(userDetail);
        List<Language> languages = languageRepository.findAllByUserDetail(userDetail);
        List<Nationality> nationalities = nationalityRepository.findAllByUserDetail(userDetail);
        Github github = githubService.findBySocial(social);
        return new DeveloperDto.AdminDeveloper(
                account,
                userDetail,
                userDetail.getAcademic(),
                social,
                github,
                github.getProjects(),
                skills,
                languages,
                nationalities
        );
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

        Academic academic = new Academic();
        academic.setUserDetail(userDetail);
        academic.setDegree(academicDetail.getDegree().toString());
        academic.setMajor(academicDetail.getMajor());
        academic.setStartDate(academicDetail.getStartDate());
        academic.setEndDate(academicDetail.getEndDate());
        academic.setSchool(academicDetail.getSchool());

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
        System.out.println(request.backgroundInformation().nationalities());
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
        System.out.println(request.backgroundInformation().spokenLanguages().entrySet());
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

    public void deleteDeveloperById(UUID id) {
        accountRepository.deleteById(id);
    }

    @Transactional
    public DeveloperDto.Response updateDeveloper(UUID developerId, DeveloperDto.AdminDeveloper request) {


        System.out.println("Github:" + cutUrl(request.github().getUrl()));

        Account developer = getDeveloperById(developerId);
        developer.setEmail(request.account().getEmail());
        developer.setRole(request.account().getRole().name());
        Account account = accountRepository.save(developer);

        Map<String, Fluency> spokenLanguagesMap = request.languages().stream()
                .collect(Collectors.toMap(
                        Language::getLanguage,
                        Language::getFluency)
                );

        UserDetail userDetail = userDetailRepository.findByAccount(account)
                .orElseThrow(() -> new NoSuchElementException("Developer not found"));
        userDetail.setAccount(null);
        account.setUserDetail(null);
        userDetailRepository.delete(userDetail);
        DeveloperDto.Request req = new DeveloperDto.Request(
                request.account().getEmail(),
                request.userDetail().getName(),
                request.account().getRole().name(),
                request.userDetail().getIntroduction(),
                request.userDetail().getPhoneNumber(),
                request.userDetail().getBootcamp().toString(),
                cutUrl(request.github().getUrl()),
                cutUrl(request.social().getLinkedInUrl()),
                cutUrl(request.social().getCodewarsUrl()),
                request.projects().stream().map(Project::getUrl).toList(),
                new DeveloperDto.BackgroundInformation(
                        request.nationalities().stream().map(Nationality::getNationality).toList(),
                        spokenLanguagesMap,
                        request.academic(),
                        request.skills().stream().map(Skill::getSkill).toList()
                )

        );
        UserDetail newUserDetails = createUserDetail(req, account);
        account.setUserDetail(newUserDetails);
        createAcademic(req, newUserDetails);
        createNationality(req, newUserDetails);
        createLanguage(req, newUserDetails);
        createSkill(req, newUserDetails);
        Social social = createSocial(req, newUserDetails);
        Github github = githubService.createGithub(req, social);
        githubService.createProject(req, github);
        List<DeveloperDto.RadarGraph> radarGraphs = scoreService.calculateRadarGraph(account);
        return DeveloperMapper.toDeveloperResponse(account, radarGraphs);
    }

    private String cutUrl(String url) {
        String fixedUrl = url.substring(url.lastIndexOf('/') + 1).trim();
        return fixedUrl;
    }



}
