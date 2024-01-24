package com.salt.server.score;

import com.salt.server.Account.api.dto.ScoreDto;
import com.salt.server.Account.model.Account;
import com.salt.server.Account.repository.AccountRepository;
import com.salt.server.assignment.model.Assignment;
import com.salt.server.assignment.AssignmentService;
import com.salt.server.assignment.model.Focus;
import com.salt.server.assignment.repository.CoverageRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ScoreService {

    private final ScoreRepository scoreRepository;
    private final AccountRepository accountRepository;
    private final AssignmentService assignmentService;
    private final CoverageRepository coverageRepository;

    public ScoreService(ScoreRepository scoreRepository, AccountRepository accountRepository, AssignmentService assignmentService, CoverageRepository coverageRepository) {
        this.scoreRepository = scoreRepository;
        this.accountRepository = accountRepository;
        this.assignmentService = assignmentService;
        this.coverageRepository = coverageRepository;
    }

    public ScoreDto.ScoreListResponse getAllScoreById(UUID id) {
        List<Score> scores = scoreRepository.findAllByAccount_Id(id);
        return new ScoreDto.ScoreListResponse( scores.stream().map(score -> new ScoreDto.ScoreResponse(score.getId(), score.getAssignment().getName(),score.getScore())).collect(Collectors.toList()));
    }

    public ScoreDto.ScoreResponse addScore(UUID id, ScoreDto.ScoreRequest request) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Account not found"));
        Assignment assignment = assignmentService.getTestByName(request.name());
        Score score = new Score();
        score.setAccount(account);
        score.setAssignment(assignment);
        score.setScore(request.score());
        score.setDescription(request.description());
        Score saveScore = scoreRepository.save(score);
        return new ScoreDto.ScoreResponse(saveScore.getId(),saveScore.getAssignment().getName(), saveScore.getScore());
    }

    public List<ScoreDto.ScoreResponse> addListOfScores(UUID id, List<ScoreDto.ScoreRequest> requests) {
        List<ScoreDto.ScoreResponse> scoreResponses = new ArrayList<>();
        for(var score: requests) {
            scoreResponses.add(addScore(id,score));
        }
        return scoreResponses;
    }

    public Map<String, Double> calculateRadarGraph(UUID id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Account not found"));
        Map<String, Double> radarGraph = new HashMap<>();
        for(var focus : Focus.values()) {
            List<Score> allScore = account.getScores();
            double total = 0;
            for(var score: allScore) {
                double percentage = (double) coverageRepository.findByAssignment_IdAndFocus(score.getAssignment().getId(), focus).getPercentage() /100;
                total += score.getScore() * percentage;
                System.out.println();
                System.out.println("score = " + score + " Percentage = " +  percentage);
                System.out.println(focus +" , " + score.getAssignment().getName() + " with score " + total);
            }
            radarGraph.put(focus.name(),total);
            total = 0;
        }
        return radarGraph;
    }

    public void deleteScore(UUID id) {
        Score score = scoreRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Account not found"));
        scoreRepository.delete(score);
    }
}
