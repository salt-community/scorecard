package com.salt.server.Account.mapper;

import com.salt.server.Account.api.dto.AccountDto;
import com.salt.server.Account.api.dto.DeveloperDto;
import com.salt.server.Account.model.Account;

public class DeveloperMapper {

    public static DeveloperDto.ShowcaseResponse toShowcaseResponse(Account account) {
        return new DeveloperDto.ShowcaseResponse(
                account.getId(),
                account.getUserDetail().getName(),
                account.getUserDetail().getSocial().getGithubId().getPictureUrl(),
                account.getUserDetail().getIntroduction()
        );
    }
}
