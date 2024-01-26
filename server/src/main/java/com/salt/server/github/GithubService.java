package com.salt.server.github;

import com.salt.server.Account.api.dto.DeveloperDto;
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

    public Github createGithub(DeveloperDto.Request request, Social social) {
        Github github = new Github();
        github.setSocial(social);
        github.setUrl(request.githubUsername());
        github.setPictureUrl(request.githubUsername());

        social.setGithubId(github);
        return githubRepository.save(github);
    }

    public void createProject(DeveloperDto.Request request, Github github) {
        for (String project : request.selectedProjects()) {
            Project newProject = new Project();
            newProject.setGithub(github);
            newProject.setUrl(project);

            github.addProject(newProject);
            projectRepository.save(newProject);
        }
    }
}
