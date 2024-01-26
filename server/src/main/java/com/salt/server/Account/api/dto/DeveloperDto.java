package com.salt.server.Account.api.dto;

import com.salt.server.Account.model.Academic;
import com.salt.server.Account.model.Fluency;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface DeveloperDto {

    record ShowcaseResponse(
            String id,
            String name,
            String profilePicture,
            String standoutIntro
    ) {
    }

    record Request(
            String email,
            String name,
            String role,
            String standoutIntro,
            String bootcamp,
            String githubUsername,
            String linkedinUsername,
            String codewarsUsername,
            List<String> selectedProjects,
            DeveloperDto.BackgroundInformation backgroundInformation
    ) {
    }

    record Response(
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
            List<DeveloperDto.RadarGraph> radarGraph,
            List<DeveloperDto.Scores> scores,
            List<DeveloperDto.ProjectDto> selectedProjects,
            DeveloperDto.BackgroundInformation backgroundInformation
    ) {
    }

    record RadarGraph(
            String subject,
            Double score,
            Integer fullmark
    ) {
    }

    record Scores(
            String scoreName,
            Map<String, Integer> data
    ) {
    }

    record ProjectDto(
            String name,
            String repoUrl,
            DeveloperDto.GithubData data
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

    record BackgroundInformation(
            List<String> nationalities,
            Map<String, Fluency> spokenLanguages,
            Academic academic,
            List<String> skills
    ) {
    }
}
