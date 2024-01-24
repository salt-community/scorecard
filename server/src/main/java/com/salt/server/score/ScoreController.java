package com.salt.server.score;

import com.salt.server.Account.api.dto.ScoreDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/score")
public class ScoreController {

    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @GetMapping("/{accountId}")
    public ScoreDto.ScoreListResponse getScoreById(@PathVariable UUID accountId) {
        return scoreService.getAllScoreById(accountId);
    }

    @PostMapping("/{accountId}/score")
    public ScoreDto.ScoreResponse scoreById(@PathVariable UUID accountId, @RequestBody ScoreDto.ScoreRequest request) {
        return scoreService.addScore(accountId, request);
    }

    @PostMapping("/{accountId}/scores")
    public List<ScoreDto.ScoreResponse> listScoresById(@PathVariable UUID accountId, @RequestBody List<ScoreDto.ScoreRequest> request) {
        return scoreService.addListOfScores(accountId, request);
    }

    @DeleteMapping("/{scoreId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteScore (@PathVariable UUID scoreId) {
        scoreService.deleteScore(scoreId);
    }

}
