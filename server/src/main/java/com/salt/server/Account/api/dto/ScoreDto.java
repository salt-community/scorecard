package com.salt.server.Account.api.dto;

import java.util.List;
import java.util.UUID;

public interface ScoreDto {

    record ScoreRequest(
            String name,
            int score,
            String description
    ) {
    }

    record ScoreResponse(
            UUID id,
            String name,
            int score

    ) {
    }

    record ScoreListResponse(
            List<ScoreResponse> scoreResponseList
    ) {
    }
}
