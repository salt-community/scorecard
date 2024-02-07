package com.salt.server.v1.account.api.dto;

import java.util.UUID;

public interface AccountDto {

    record Request(
            String email,
            String name,
            String role
    ) {
    }

    record Response(
            UUID id,
            String email,
            String role
    ) {
    }


    record CoreTeamResponse(
            UUID id,
            String name,
            String email,
            String role
    ) {
    }

}
