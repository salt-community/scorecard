package com.salt.server.Account.api.dto;

public record ScoreRequest(
        String name,
        int score,
        String description
) {
}
