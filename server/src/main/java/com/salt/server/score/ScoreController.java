package com.salt.server.score;

import com.salt.server.Account.api.dto.ScoreListResponse;
import com.salt.server.Account.api.dto.ScoreRequest;
import com.salt.server.Account.api.dto.ScoreResponse;
import org.springframework.http.HttpStatus;
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
    public ScoreListResponse getScoreById(@PathVariable UUID accountId) {
        return scoreService.getAllScoreById(accountId);
    }

    @PostMapping("/{accountId}/scoring")
    public ScoreResponse scoreById(@PathVariable UUID accountId, @RequestBody ScoreRequest request) {
        return scoreService.addScore(accountId, request);
    }

    @DeleteMapping("/{scoreId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteScore (@PathVariable UUID scoreId) {
        scoreService.deleteScore(scoreId);
    }

}
