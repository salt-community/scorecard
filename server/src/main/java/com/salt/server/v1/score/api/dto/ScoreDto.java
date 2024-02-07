package com.salt.server.v1.score.api.dto;

import java.util.List;
import java.util.UUID;

public interface ScoreDto {

    record Request(
            String name,
            String score,
            String description
    ) {
    }

    record DeleteRequest(
            String id
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
