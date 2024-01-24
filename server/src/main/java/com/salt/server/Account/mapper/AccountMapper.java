package com.salt.server.Account.mapper;

import com.salt.server.Account.api.dto.AccountDto;
import com.salt.server.Account.model.Account;
import com.salt.server.Account.model.Language;
import com.salt.server.Account.model.Nationality;
import com.salt.server.Account.model.Skill;
import com.salt.server.assignment.model.Assignment;
import com.salt.server.assignment.model.Focus;
import com.salt.server.assignment.model.Type;
import com.salt.server.score.Score;

import java.util.*;
import java.util.stream.Collectors;

public class AccountMapper {

    public static AccountDto.AccountResponse toAccountResponse(Account account, List<AccountDto.RadarGraph> radarGraphs) {
        AccountDto.BackgroundInformation backgroundInformation = createBackgroundInformation(account);
        List<AccountDto.ProjectDto> projects = createProjectDto(account);
        List<AccountDto.Scores> scores = createScores(account);

        return new AccountDto.AccountResponse(
                account.getId().toString(),
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

    private static List<AccountDto.Scores> createScores(Account account) {
        List<AccountDto.Scores> scoresList = new ArrayList<>();

        for(var type: Type.values()) {
            AccountDto.Scores scores = new AccountDto.Scores(
                    type.toString(),
                    account.getScores().stream()
                            .filter(score -> score.getAssignment().getType().equals(type) )
                            .collect(Collectors.toMap(score -> score.getAssignment().getName(), Score::getScore))
            );
           scoresList.add(scores);
        }
        return  scoresList;
    }

    private static AccountDto.BackgroundInformation createBackgroundInformation(Account account) {
        return new AccountDto.BackgroundInformation(
                account.getUserDetail().getNationality().stream().map(Nationality::getNationality).toList(),
                account.getUserDetail().getLanguages().stream().collect(Collectors.toMap(Language::getLanguage,Language::getFluency)),
                account.getUserDetail().getAcademic(),
                account.getUserDetail().getSkills().stream().map(Skill::getSkill).toList()
        );
    }

    private static List<AccountDto.ProjectDto> createProjectDto(Account account) {
        return account.getUserDetail().getSocial().getGithubId().getProject().stream()
                .map(data -> new AccountDto.ProjectDto(
                        data.getUrl().substring(data.getUrl().lastIndexOf("/")+1),
                        data.getUrl(),
                        new AccountDto.GithubData(
                                data.getCommit(),
                                data.getIssue(),
                                data.getDuration(),
                                data.getPerformance(),
                                data.getTestCoverage()
                        ))).toList();
    }


}
