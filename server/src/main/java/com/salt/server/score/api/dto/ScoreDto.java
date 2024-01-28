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
            String type,
            String assignment,
            Integer score,
            String description
    ) {
    }

    record ScoreListResponse(
            List<Response> scoreResponseList
    ) {
    }
}
