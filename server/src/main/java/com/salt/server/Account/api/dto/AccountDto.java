package com.salt.server.Account.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.salt.server.Account.model.Academic;
import com.salt.server.Account.model.Fluency;
import com.salt.server.Account.model.Language;
import com.salt.server.Account.model.Skill;
import com.salt.server.github.Project;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface AccountDto {
    record AccountRequest(
            String email,
            String name,
            String standoutIntro,
            String bootcamp,
            String githubUsername,
            String linkedinUsername,
            String codewarsUsername,
            List<String> selectedProjectUrls,
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

    record AccountResponse(
            String id,
            String email,
            String name,
            String standoutIntro,
            String bootcamp,
            String githubUrl,
            String githubUserName,
            String githubProfilePictureUrl,
            String linkedinUrl,
            String codewarsUrl,
            List<Scores> scores,
            List<ProjectDto> selectedProjectUrls,
            BackgroundInformation backgroundInformation
    ) {
    }

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
}
