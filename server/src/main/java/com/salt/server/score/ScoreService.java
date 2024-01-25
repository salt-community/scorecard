package com.salt.server.score;

import com.salt.server.Account.api.dto.AccountDto;
import com.salt.server.Account.api.dto.ScoreDto;
import com.salt.server.Account.model.Account;
import com.salt.server.assignment.model.Assignment;
import com.salt.server.assignment.AssignmentService;
import com.salt.server.assignment.model.Focus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ScoreService {

    private final ScoreRepository scoreRepository;
    private final AssignmentService assignmentService;

    public ScoreService(ScoreRepository scoreRepository,
                        AssignmentService assignmentService) {
        this.scoreRepository = scoreRepository;
        this.assignmentService = assignmentService;
    }

    public ScoreDto.ScoreListResponse getAllScoreById(UUID id) {
        List<Score> scores = scoreRepository.findAllByAccount_Id(id);
        return new ScoreDto.ScoreListResponse(scores.stream()
                .map(score -> new ScoreDto.ScoreResponse(
                        score.getId(),
                        score.getAssignment().getName(),
                        score.getScore()))
                .collect(Collectors.toList()));
    }

    public ScoreDto.ScoreResponse addScore(Account account, ScoreDto.ScoreRequest request) {
        Assignment assignment = assignmentService.getTestByName(request.name());
        Score score = Score.builder()
                .account(account)
                .assignment(assignment)
                .score(request.score())
                .description(request.description())
                .build();

        account.addScore(score);
        assignment.addScore(score);
        Score saveScore = scoreRepository.save(score);
        return new ScoreDto.ScoreResponse(
                saveScore.getId(),
                saveScore.getAssignment().getName(),
                saveScore.getScore());
    }

    public List<ScoreDto.ScoreResponse> addListOfScores(Account account, List<ScoreDto.ScoreRequest> requests) {
        List<ScoreDto.ScoreResponse> scoreResponses = new ArrayList<>();
        for (var score : requests) {
            scoreResponses.add(addScore(account, score));
        }
        return scoreResponses;
    }

    public List<AccountDto.RadarGraph> calculateRadarGraph(Account account) {
        List<AccountDto.RadarGraph> radarGraphs = new ArrayList<>();
        for (var focus : Focus.values()) {

            double totalScore = 0;
            double totalPercentage = 0;
            if (account.getScores() != null) {
                for (var score : account.getScores()) {
                    double percentage = (double) assignmentService
                            .getCoverageByAssignmentAndFocus(score.getAssignment(), focus)
                            .getPercentage() / 100;

                    totalScore += score.getScore() * percentage;
                    totalPercentage += percentage;
                }
            }
            radarGraphs.add(new AccountDto.RadarGraph(focus.name(), totalScore / totalPercentage, 100));
        }
        return radarGraphs;
    }

    public void deleteScore(UUID id) {
        Score score = scoreRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Account not found"));
        scoreRepository.delete(score);
    }
}
