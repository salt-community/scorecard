package com.salt.server.Account.api.dto;

import com.salt.server.Account.model.*;
import com.salt.server.github.model.Github;
import com.salt.server.github.model.Project;
import com.salt.server.score.Score;

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

    record AdminResponse(
            String id,
            String name,
            String email,
            String phoneNumber,
            String role
    ) {
    }

    record ScoreboardResponse(
            String id,
            String name,
            List<DeveloperDto.RadarGraph> radarGraph
    ) {
    }

    record DeveloperScoreboardResponse(
            String id,
            String name,
            String bootcamp,
            String githubProfilePictureUrl,
            String githubUrl,
            String linkedinUsername,
            List<DeveloperDto.RadarGraph> radarGraph,
            List<DeveloperDto.ScoreDetail> scores
    ) {
    }

    record ScoreScoreboard(
            String scoreName,
            List<ScoreDetail> data
    ) {
    }

    record ScoreDetail(
            String id,
            String type,
            String assignment,
            Integer score,
            String description
    ) {
    }

    record Request(
            String email,
            String name,
            String role,
            String standoutIntro,
            String phoneNumber,
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
            List<DeveloperDto.ScoreDetail> scores,
            List<DeveloperDto.ProjectDto> selectedProjects,
            DeveloperDto.BackgroundInformation backgroundInformation
    ) {
    }

    record AdminDeveloper(
            Account account,
            UserDetail userDetail,
            Academic academic,
            Social social,
            Github github,
            List<Project> projects,
            List<Skill> skills,
            List<Language> languages,
            List<Nationality> nationalities
    ){}

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
