package com.salt.server.score;

import com.salt.server.Account.api.dto.DeveloperDto;
import com.salt.server.Account.model.Account;
import com.salt.server.Account.service.DeveloperService;
import com.salt.server.assignment.model.Assignment;
import com.salt.server.assignment.AssignmentService;
import com.salt.server.assignment.model.Focus;
import com.salt.server.score.api.dto.ScoreDto;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ScoreService {

    private final ScoreRepository scoreRepository;
    private final DeveloperService developerService;
    private final AssignmentService assignmentService;

    public ScoreService(ScoreRepository scoreRepository, DeveloperService developerService,
                        AssignmentService assignmentService) {
        this.scoreRepository = scoreRepository;
        this.developerService = developerService;
        this.assignmentService = assignmentService;
    }

    public ScoreDto.ScoreListResponse getAllScoreById(UUID id) {
        List<Score> scores = scoreRepository.findAllByAccount_Id(id);
        return new ScoreDto.ScoreListResponse(scores.stream()
                .map(score -> new ScoreDto.Response(
                        score.getId(),
                        score.getAssignment().getName(),
                        score.getScore()))
                .collect(Collectors.toList()));
    }

    public ScoreDto.Response addScore(UUID id, ScoreDto.Request request) {
        Account account = developerService.getDeveloperById(id);
        Assignment assignment = assignmentService.getTestByName(request.name());
        Score score = new Score();
        score.setAccount(account);
        score.setAssignment(assignment);
        score.setScore(request.score());
        score.setDescription(request.description());

        Score saveScore = scoreRepository.save(score);
        account.addScore(saveScore);
        assignment.addScore(saveScore);

        return new ScoreDto.Response(
                saveScore.getId(),
                saveScore.getAssignment().getName(),
                saveScore.getScore());
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
                .orElseThrow(() -> new NoSuchElementException("Account not found"));
        scoreRepository.delete(score);
    }
}
