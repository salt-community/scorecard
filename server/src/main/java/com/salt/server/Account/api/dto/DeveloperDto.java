package com.salt.server.Account.api.dto;

import java.util.UUID;

public interface DeveloperDto {

    record ShowcaseResponse(
            UUID id,
            String name,
            String profilePicture,
            String standoutIntro
    ){}
}
