package com.salt.server.score;

import com.salt.server.Account.api.dto.AccountDto;
import com.salt.server.Account.api.dto.ScoreDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("api/scores")
public class ScoreController {

    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @GetMapping("/{accountId}")
    public ScoreDto.ScoreListResponse getScoreById(@PathVariable UUID accountId) {
        return scoreService.getAllScoreById(accountId);
    }

    @PostMapping("/{accountId}/add")
    public ScoreDto.ScoreResponse addScoreById(@PathVariable UUID accountId, @RequestBody ScoreDto.ScoreRequest request) {
        return scoreService.addScore(accountId, request);
    }

    @PostMapping("/{accountId}/add-scores")
    public List<ScoreDto.ScoreResponse> addListScoresById(@PathVariable UUID accountId, @RequestBody List<ScoreDto.ScoreRequest> request) {
        return scoreService.addListOfScores(accountId, request);
    }

    @DeleteMapping("/{scoreId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteScore (@PathVariable UUID scoreId) {
        scoreService.deleteScore(scoreId);
    }

}
