package com.salt.server.v1.account.mapper;

import com.salt.server.v1.account.api.dto.DeveloperDto;
import com.salt.server.v1.account.model.*;
import com.salt.server.v1.assignment.model.Type;
import com.salt.server.v1.score.Score;
import com.salt.server.v1.account.model.Account;
import com.salt.server.v1.account.model.Language;
import com.salt.server.v1.account.model.Nationality;
import com.salt.server.v1.account.model.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DeveloperMapper {


    public static DeveloperDto.ShowcaseResponse toShowcaseResponse(Account account) {
        return new DeveloperDto.ShowcaseResponse(
                account.getId().toString(),
                account.getUserDetail().getName(),
                account.getUserDetail().getSocial().getGithubId().getPictureUrl(),
                account.getUserDetail().getIntroduction()
        );
    }


    public static DeveloperDto.Response toDeveloperResponse(Account account, List<DeveloperDto.RadarGraph> radarGraphs) {
        DeveloperDto.BackgroundInformation backgroundInformation = createBackgroundInformation(account);
        List<DeveloperDto.ProjectDto> projects = createProjectDto(account);
        List<DeveloperDto.ScoreDetail> scores = createScores(account);
        List<DeveloperDto.Average> averages = createAverages(account.getScores());
        return new DeveloperDto.Response(
                account.getId(),
                account.getEmail(),
                account.getUserDetail().getName(),
                account.getUserDetail().getIntroduction(),
                account.getUserDetail().getBootcamp().toString(),
                account.getUserDetail().getSocial().getGithubId().getUrl(),
                account.getUserDetail().getSocial().getGithubId().getUrl().substring(account.getUserDetail().getSocial().getGithubId().getUrl().lastIndexOf("/")+1),
                account.getUserDetail().getSocial().getGithubId().getPictureUrl(),
                account.getUserDetail().getSocial().getLinkedInUrl(),
                account.getUserDetail().getSocial().getCodewarsUrl(),
                radarGraphs,
                scores,
                projects,
                backgroundInformation,
                averages
        );
    }

    private static List<DeveloperDto.Average> createAverages(List<Score> scores) {
        List<DeveloperDto.Average> averages = new ArrayList<>();
        for (Type type : Type.values()) {
            List<Score> filteredScores = scores.stream()
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
        return averages;
    }
    private static List<DeveloperDto.ScoreDetail> createScores(Account account) {
        return account.getScores().stream()
                .map(score -> new DeveloperDto.ScoreDetail(
                        score.getId().toString(),
                        score.getAssignment().getType().toString(),
                        score.getAssignment().getName(),
                        score.getScore(),
                        score.getDescription()))
                .toList();
    }

    private static DeveloperDto.BackgroundInformation createBackgroundInformation(Account account) {
        return new DeveloperDto.BackgroundInformation(
                account.getUserDetail().getNationality().stream().map(Nationality::getNationality).toList(),
                account.getUserDetail().getLanguages().stream().collect(Collectors.toMap(Language::getLanguage,Language::getFluency)),
                account.getUserDetail().getAcademic(),
                account.getUserDetail().getSkills().stream().map(Skill::getSkill).toList()
        );
    }

    private static List<DeveloperDto.ProjectDto> createProjectDto(Account account) {
        return account.getUserDetail().getSocial().getGithubId().getProjects().stream()
                .map(data -> new DeveloperDto.ProjectDto(
                        data.getUrl().substring(data.getUrl().lastIndexOf("/")+1),
                        data.getUrl(),
                        new DeveloperDto.GithubData(
                                data.getCommit(),
                                data.getIssue(),
                                data.getDuration(),
                                data.getPerformance(),
                                data.getTestCoverage()
                        ))).toList();
    }
}
