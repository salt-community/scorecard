package com.salt.server.Account.api.dto;

import java.util.UUID;

public interface AccountDto {

    record Request(
            String email,
            String name,

            String phoneNumber,

            String role
    ) {
    }

    record Response(
            UUID id,
            String email,
            String role
    ) {
    }

}
