package com.salt.server.Account.api.dto;

import com.salt.server.Account.model.UserDetail;
import com.salt.server.github.Github;
import com.salt.server.Account.model.Bootcamp;
import com.salt.server.Account.model.Social;

import java.util.UUID;

public record AccountResponse(
        UUID userId,
        UserDetail userDetail,
        Social social,
        Github github

) {
}