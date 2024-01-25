package com.salt.server.Account.mapper;

import com.salt.server.Account.api.dto.DeveloperDto;
import com.salt.server.Account.model.Account;
import com.salt.server.Account.model.Language;
import com.salt.server.Account.model.Nationality;
import com.salt.server.Account.model.Skill;
import com.salt.server.assignment.model.Type;
import com.salt.server.score.Score;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DeveloperMapper {

    public static DeveloperDto.ShowcaseResponse toShowcaseResponse(Account account) {
        return new DeveloperDto.ShowcaseResponse(
                account.getId(),
                account.getUserDetail().getName(),
                account.getUserDetail().getSocial().getGithubId().getPictureUrl(),
                account.getUserDetail().getIntroduction()
        );
    }

    public static DeveloperDto.Response toDeveloperResponse(Account account, List<DeveloperDto.RadarGraph> radarGraphs) {
        DeveloperDto.BackgroundInformation backgroundInformation = createBackgroundInformation(account);
        List<DeveloperDto.ProjectDto> projects = createProjectDto(account);
        List<DeveloperDto.Scores> scores = createScores(account);

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
                backgroundInformation
        );
    }

    private static List<DeveloperDto.Scores> createScores(Account account) {
        List<DeveloperDto.Scores> scoresList = new ArrayList<>();

        for(var type: Type.values()) {
            DeveloperDto.Scores scores = new DeveloperDto.Scores(
                    type.toString(),
                    account.getScores()!=null?
                            account.getScores().stream()
                                    .filter(score -> score.getAssignment().getType().equals(type) )
                                    .collect(Collectors.toMap(score -> score.getAssignment().getName(), Score::getScore))
                            : null
            );
            scoresList.add(scores);
        }
        return  scoresList;
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
