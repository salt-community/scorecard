package com.salt.server.v1.score;

import com.salt.server.v1.account.api.dto.DeveloperDto;
import com.salt.server.v1.account.model.Account;
import com.salt.server.v1.account.service.AccountService;
import com.salt.server.v1.assignment.model.Assignment;
import com.salt.server.v1.assignment.AssignmentService;
import com.salt.server.v1.assignment.model.Focus;
import com.salt.server.v1.score.api.dto.ScoreDto;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ScoreService {

    private final ScoreRepository scoreRepository;
    private final AccountService accountService;
    private final AssignmentService assignmentService;

    public ScoreService(ScoreRepository scoreRepository,
                        AccountService accountService,
                        AssignmentService assignmentService) {
        this.scoreRepository = scoreRepository;
        this.accountService = accountService;
        this.assignmentService = assignmentService;
    }

    public ScoreDto.ScoreListResponse getAllScoreById(UUID id) {
        List<Score> scores = scoreRepository.findAllByAccount_Id(id);
        return new ScoreDto.ScoreListResponse(scores.stream()
                .map(score -> new ScoreDto.Response(
                        score.getId(),
                        score.getAssignment().getType().toString(),
                        score.getAssignment().getName(),
                        score.getScore(),
                        score.getDescription()))
                .collect(Collectors.toList()));
    }

    public ScoreDto.Response addScore(UUID id, ScoreDto.Request request) {
        Account account = accountService.getDeveloperById(id);
        Assignment assignment = assignmentService.getAssignmentByName(request.name());
        if(scoreRepository.findByAccountAndAssignment(account, assignment)!= null) {
            throw new IllegalArgumentException("Score already exist");
        };
        Score score = new Score();
        score.setAccount(account);
        score.setAssignment(assignment);
        score.setScore(Integer.parseInt(request.score()));
        score.setDescription(request.description());

        Score saveScore = scoreRepository.save(score);
        account.addScore(saveScore);
        assignment.addScore(saveScore);

        return new ScoreDto.Response(
                saveScore.getId(),
                score.getAssignment().getType().toString(),
                saveScore.getAssignment().getName(),
                saveScore.getScore(),
                saveScore.getDescription());
    }

    public List<ScoreDto.Response> addListOfScores(UUID id, List<ScoreDto.Request> requests) {
        List<ScoreDto.Response> scoreResponses = new ArrayList<>();
        for (var score : requests) {
            scoreResponses.add(addScore(id, score));
        }
        return scoreResponses;
    }

    public List<DeveloperDto.RadarGraph> calculateRadarGraph(Account account) {
        List<DeveloperDto.RadarGraph> radarGraphs = new ArrayList<>();
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
            radarGraphs.add(new DeveloperDto.RadarGraph(focus.name(), totalScore / totalPercentage, 100));
        }
        return radarGraphs;
    }

    public void deleteScore(UUID id) {
        Score score = scoreRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Score not found"));
        scoreRepository.delete(score);
    }
}
