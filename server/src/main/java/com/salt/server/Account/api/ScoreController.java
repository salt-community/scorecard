package com.salt.server.Account.api;

import com.salt.server.Account.api.dto.AccountResponse;
import com.salt.server.Account.api.dto.ScoreListResponse;
import com.salt.server.Account.api.dto.ScoreRequest;
import com.salt.server.Account.api.dto.ScoreResponse;
import com.salt.server.Account.service.ScoreService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/score")
public class ScoreController {

    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @GetMapping("/{accountId}")
    public ScoreListResponse getScoreById(@PathVariable String accountId) {
        return scoreService.getAllScoreById(UUID.fromString(accountId));
    }

    @PostMapping("/{accountId}/scoring")
    public ScoreResponse scoreById(@PathVariable String accountId, @RequestBody ScoreRequest request) {
        return scoreService.addScore(accountId, request);
    }
}
