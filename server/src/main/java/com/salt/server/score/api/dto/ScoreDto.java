package com.salt.server.score.api.dto;

import java.util.List;
import java.util.UUID;

public interface ScoreDto {

    record Request(
            String name,
            String score,
            String description
    ) {
    }

    record Response(
            UUID id,
            String name,
            int score
    ) {
    }

    record ScoreListResponse(
            List<Response> scoreResponseList
    ) {
    }
}
