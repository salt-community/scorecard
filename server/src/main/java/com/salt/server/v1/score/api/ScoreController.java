package com.salt.server.v1.score.api;

import com.salt.server.v1.score.ScoreService;
import com.salt.server.v1.score.api.dto.ScoreDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/scores")
@CrossOrigin(origins = {"http://localhost:3000", "https://scorecard-gold.vercel.app", "https://salt-scorecard.vercel.app"})
public class ScoreController {

    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @GetMapping("/{accountId}")
    public ScoreDto.ScoreListResponse getScoreById(@PathVariable UUID accountId) {
        return scoreService.getAllScoreById(accountId);
    }

    @PostMapping("/{developerId}/add-score")
    public ResponseEntity<ScoreDto.Response> addScoreById(@PathVariable UUID developerId, @RequestBody ScoreDto.Request request) {
        System.out.println(request.name());
//        return ResponseEntity.ok().body(scoreService.addScore(developerId, request)) ;
        return new ResponseEntity<>(scoreService.addScore(developerId, request),HttpStatus.OK);
    }

    @PostMapping("/{developerId}/add-scoresa")
    public List<ScoreDto.Response> addListScoresById(@PathVariable UUID developerId, @RequestBody List<ScoreDto.Request> request) {
        return scoreService.addListOfScores(developerId, request);
    }

    @DeleteMapping("/{scoreId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteScore (@PathVariable UUID scoreId) {
        scoreService.deleteScore(scoreId);
    }

}
