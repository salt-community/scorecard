package com.salt.server.Account.api.dto;

import java.util.List;

public record ScoreListResponse (
        List<ScoreResponse> scoreResponseList
) {
}
