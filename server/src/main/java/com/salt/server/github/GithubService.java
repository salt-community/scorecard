package com.salt.server.github;

import com.salt.server.Account.api.dto.AccountDto;
import com.salt.server.Account.model.Social;
import com.salt.server.github.model.Github;
import com.salt.server.github.model.Project;
import com.salt.server.github.repository.GithubRepository;
import com.salt.server.github.repository.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class GithubService {

    private final GithubRepository githubRepository;
    private final ProjectRepository projectRepository;

    public GithubService(GithubRepository githubRepository, ProjectRepository projectRepository) {
        this.githubRepository = githubRepository;
        this.projectRepository = projectRepository;
    }

    public Github createGithub(AccountDto.AccountRequest request, Social social) {
        Github github = Github.builder()
                .social(social)
                .url(request.githubUsername())
                .pictureUrl(request.githubUsername())
                .build();

        social.setGithubId(github);
        return githubRepository.save(github);
    }

    public void createProject(AccountDto.AccountRequest request, Github github) {
        for (String project : request.selectedProjects()) {
            Project newProject = Project.builder()
                    .github(github)
                    .url(project)
                    .build();

            github.addProject(newProject);
            projectRepository.save(newProject);
        }
    }
}
