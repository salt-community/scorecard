package com.salt.server.Account.api.dto;

import com.salt.server.Account.model.Academic;
import com.salt.server.Account.model.Fluency;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface AccountDto {
    record AccountRequest(
            String email,
            String name,
            String standoutIntro,
            String bootcamp,
            String githubUsername,
            String linkedinUsername,
            String codewarsUsername,
            List<String> selectedProjects,
            BackgroundInformation backgroundInformation
    ) {
    }

    record BackgroundInformation(
            List<String> nationalities,
            Map<String, Fluency> spokenLanguages,
            Academic academic,
            List<String> skills
    ) {
    }
    record AccountResponseTest(
            UUID id,
            String email,
            String name,
            String standoutIntro,
            String bootcamp,
            String githubUrl,
            String githubUserName,
            String githubProfilePictureUrl,
            String linkedinUrl,
            String codewarsUrl,
            List<RadarGraph> radarGraph,
            List<Scores> scores,
            List<ProjectDto> selectedProjects,
            BackgroundInformation backgroundInformation
            ) {
    }

    record AccountResponse(
            UUID id,
            String email,
            String name,
            String standoutIntro,
            String bootcamp,
            String githubUrl,
            String githubUserName,
            String githubProfilePictureUrl,
            String linkedinUrl,
            String codewarsUrl,
            List<RadarGraph> radarGraph,
            List<Scores> scores,
            List<ProjectDto> selectedProjects,
            BackgroundInformation backgroundInformation
    ) {
    }

    record RadarGraph(
            String subject,
            Double score,
            Integer fullmark
    ){}

    record Scores(
            String ScoreName,
            Map<String, Integer> data
    ) {
    }


    record ProjectDto(
            String name,
            String repoUrl,
            GithubData data
    ) {
    }

    record GithubData(
            int commits,
            int issues,
            int duration,
            int performance,
            int testCoverages
    ) {
    }

    record ListAccountsDto(
            UUID id,
            String name,
            String profilePicture,
            String standoutIntro
    ){}
}
