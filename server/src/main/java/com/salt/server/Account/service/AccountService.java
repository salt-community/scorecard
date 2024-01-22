package com.salt.server.Account.service;

import com.salt.server.Account.api.dto.AccountDto;
import com.salt.server.Account.model.*;
import com.salt.server.Account.repository.AccountRepository;
import com.salt.server.Account.repository.SocialRepository;
import com.salt.server.Account.repository.UserDetailRepository;
import com.salt.server.github.Github;
import com.salt.server.github.GithubRepository;
import com.salt.server.github.Project;
import com.salt.server.github.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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

//    public AccountDto.AccountResponse getAccountById(String id) {
//        Account account = accountRepository.findById(UUID.fromString(id))
//                .orElseThrow(() -> new NoSuchElementException("Account not found"));
//        UserDetail userDetail = userDetailRepository.findByAccount_Id(UUID.fromString(id))
//                .orElseThrow(() -> new NoSuchElementException("userDetail not found"));
//        return new AccountDto.AccountResponse(account.getId(), userDetail, userDetail.getSocial(), userDetail.getSocial().getGithubId());
//    }

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
        userDetail.setEducation(request.backgroudInformations().educations());
        userDetail.setSkills(request.backgroudInformations().skills());
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

        AccountDto.BackgroudInformations backgroudInformations = new AccountDto.BackgroudInformations(
                userDetail.getNationality(),
                userDetail.getLanguages(),
                userDetail.getEducation(),
                userDetail.getSkills()
        );

        List<AccountDto.ProjectDto> projects = projectRepository.findAllByGithubId(saveGithub.getId()).stream()
                .map(data -> new AccountDto.ProjectDto(
                        data.getUrl().substring(data.getUrl().lastIndexOf("/")+1),
                        data.getUrl(),
                        new AccountDto.Data(
                                data.getCommit(),
                                data.getIssue(),
                                data.getDuration(),
                                data.getPerformance(),
                                data.getTestCoverage()
                        ))).collect(Collectors.toList());


        return new AccountDto.AccountResponse(
                saveAccount.getId().toString(),
                saveAccount.getUsername(),
                saveUserDetail.getName(),
                saveUserDetail.getIntroduction(),
                saveUserDetail.getBootcamp().toString(),
                saveGithub.getUrl(),
                saveGithub.getUrl().substring(saveGithub.getUrl().lastIndexOf("/")+1),
                saveGithub.getPictureUrl(),
                saveSocial.getLinkedInUrl(),
                saveSocial.getCodewarsUrl(),
                projects,
                backgroudInformations
        );
    }

    public void createDeveloperAccountCSV(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            int index = 1;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                Account account = createAccount(data);
                UserDetail userDetail = createUserDetail(account, data);
                Social social = createSocial(userDetail, data);
                createGithub(social, data);
            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Github createGithub(Social social, String[] newUser) {
        Github github = new Github();
        github.setSocial(social);
        github.setUrl(newUser[4]);
        github.setPictureUrl(newUser[4]);

        return githubRepository.save(github);

    }

    private Social createSocial(UserDetail userDetail, String[] newUser) {
        Social social = new Social();
        social.setUserDetail(userDetail);
        social.setLinkedInUrl(newUser[5]);
        social.setCodewarsUrl(newUser[6]);

        return socialRepository.save(social);
    }

    private UserDetail createUserDetail(Account account, String[] newUser) {
        UserDetail userDetail = new UserDetail();
        userDetail.setAccount(account);
        userDetail.setBootcamp(newUser[1]);
        userDetail.setName(newUser[2]);
        userDetail.setNationality(newUser[7]);
        userDetail.setLanguages(newUser[8]);
        userDetail.setEducation(newUser[9]);
        userDetail.setSkills(newUser[10]);

        return userDetailRepository.save(userDetail);
    }

    private Account createAccount(String[] newUser) {
        Account newAccount = new Account();
        newAccount.setUsername(newUser[3]);

        return accountRepository.save(newAccount);
    }
}
