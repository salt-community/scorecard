package com.salt.server.Account.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.salt.server.Account.model.Academic;
import com.salt.server.Account.model.Language;
import com.salt.server.Account.model.Skill;
import com.salt.server.github.Project;

import java.util.List;

public interface AccountDto {
    record AccountRequest(
            String username,
            String name,
            String standoutIntro,
            String bootcamp,
            String githubUrl,
            String linkedinUrl,
            String codewarsUrl,
            List<String> selectedProjectUrls,
            @JsonProperty("BackgroundInformation")
            BackgroundInformation backgroundInformation
    ) {
    }

    record BackgroundInformation(
            String nationalities,
            List<Language> spokenLanguages,
            Academic educations,
            List<Skill> skills
    ) {
    }

    record AccountResponse(
            String id,
            String username,
            String name,
            String standoutIntro,
            String bootcamp,
            String githubUrl,
            String githubUserName,
            String githubProfilePictureUrl,
            String linkedinUrl,
            String codewarsUrl,
            List<ProjectDto> selectedProjectUrls,
            BackgroundInformation backgroundInformation
    ) {
    }

    record ProjectDto(
            String name,
            String repoUrl,
            Data projectDetailDto
    ) {}

    record Data(
            int commits,
            int issues,
            int duration,
            int performance,
            int testCoverages
    ) {}
}
