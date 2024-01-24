package com.salt.server.Account.service;

import com.salt.server.Account.api.dto.AccountDto;
import com.salt.server.Account.mapper.AccountMapper;
import com.salt.server.Account.model.*;
import com.salt.server.Account.repository.*;
import com.salt.server.github.Github;
import com.salt.server.github.GithubRepository;
import com.salt.server.github.Project;
import com.salt.server.github.ProjectRepository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
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

    public AccountService(AccountRepository accountRepository, UserDetailRepository userDetailRepository, SocialRepository socialRepository, GithubRepository githubRepository, ProjectRepository projectRepository, LanguageRepository languageRepository, SkillRepository skillRepository, AcademicRepository academicRepository, NationalityRepository nationalityRepository) {
        this.accountRepository = accountRepository;
        this.userDetailRepository = userDetailRepository;
        this.socialRepository = socialRepository;
        this.githubRepository = githubRepository;
        this.projectRepository = projectRepository;
        this.languageRepository = languageRepository;
        this.skillRepository = skillRepository;
        this.academicRepository = academicRepository;
        this.nationalityRepository = nationalityRepository;
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
        account.setEmail(request.email());
        Account saveAccount = accountRepository.save(account);

        UserDetail userDetail = new UserDetail();
        userDetail.setAccount(saveAccount);
        userDetail.setName(request.name());
        userDetail.setIntroduction(request.standoutIntro());
        userDetail.setBootcamp(request.bootcamp());
        UserDetail saveUserDetail = userDetailRepository.save(userDetail);

        Academic academic = new Academic();
        academic.setUserDetail(userDetail);
        academic.setDegree(request.backgroundInformation().academic().getDegree());
        academic.setMajor(request.backgroundInformation().academic().getMajor());
        academic.setStartDate(request.backgroundInformation().academic().getStartDate());
        academic.setEndDate(request.backgroundInformation().academic().getEndDate());
        academic.setSchool(request.backgroundInformation().academic().getSchool());
        academicRepository.save(academic);

        for (var nationality : request.backgroundInformation().nationalities()) {
            Nationality newNationality = new Nationality();
            newNationality.setUserDetail(userDetail);
            newNationality.setNationality(nationality);
            nationalityRepository.save(newNationality);
        }

        for (var language : request.backgroundInformation().spokenLanguages().entrySet()) {
            Language newLanguage = new Language();
            newLanguage.setUserDetail(userDetail);
            newLanguage.setLanguage(language.getKey());
            newLanguage.setFluency(language.getValue().toString());
            languageRepository.save(newLanguage);
        }

        for (var skill : request.backgroundInformation().skills()) {
            Skill newSkill = new Skill();
            newSkill.setUserDetail(userDetail);
            newSkill.setSkill(skill);
            skillRepository.save(newSkill);
        }

        Social social = new Social();
        social.setUserDetail(saveUserDetail);
        social.setLinkedInUrl(request.linkedinUsername());
        social.setCodewarsUrl(request.codewarsUsername());
        Social saveSocial = socialRepository.save(social);

        Github github = new Github();
        github.setSocial(saveSocial);
        github.setUrl(request.githubUsername());
        github.setPictureUrl(request.githubUsername());
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
