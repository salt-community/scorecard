package com.salt.server.Account.mapper;

import com.salt.server.Account.api.dto.AccountDto;
import com.salt.server.Account.model.Account;

import java.util.List;
import java.util.stream.Collectors;

public class AccountMapper {

    public static AccountDto.AccountResponse toAccountResponse(Account account) {
        AccountDto.BackgroundInformation backgroundInformation = createBackgroundInformation(account);
        List<AccountDto.ProjectDto> projects = createProjectDto(account);

        return new AccountDto.AccountResponse(
                account.getId().toString(),
                account.getUsername(),
                account.getUserDetail().getName(),
                account.getUserDetail().getIntroduction(),
                account.getUserDetail().getBootcamp().toString(),
                account.getUserDetail().getSocial().getGithubId().getUrl(),
                account.getUserDetail().getSocial().getGithubId().getUrl().substring(account.getUserDetail().getSocial().getGithubId().getUrl().lastIndexOf("/")+1),
                account.getUserDetail().getSocial().getGithubId().getPictureUrl(),
                account.getUserDetail().getSocial().getLinkedInUrl(),
                account.getUserDetail().getSocial().getCodewarsUrl(),
                projects,
                backgroundInformation
        );
    }

    private static AccountDto.BackgroundInformation createBackgroundInformation(Account account) {
        return new AccountDto.BackgroundInformation(
                account.getUserDetail().getNationality(),
                account.getUserDetail().getLanguages(),
                account.getUserDetail().getAcademic(),
                account.getUserDetail().getSkills()
        );
    }

    private static List<AccountDto.ProjectDto> createProjectDto(Account account) {
        return account.getUserDetail().getSocial().getGithubId().getProject().stream()
                .map(data -> new AccountDto.ProjectDto(
                        data.getUrl().substring(data.getUrl().lastIndexOf("/")+1),
                        data.getUrl(),
                        new AccountDto.Data(
                                data.getCommit(),
                                data.getIssue(),
                                data.getDuration(),
                                data.getPerformance(),
                                data.getTestCoverage()
                        ))).collect(Collectors.toList());
    }
}
