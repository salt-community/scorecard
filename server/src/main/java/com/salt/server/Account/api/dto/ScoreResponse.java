package com.salt.server.Account.api.dto;

import java.util.UUID;

public record ScoreResponse(
        UUID id,
        String name,
        int score

) {
}
