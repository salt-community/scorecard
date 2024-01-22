package com.salt.server.Account.service;

import com.salt.server.Account.api.dto.ScoreListResponse;
import com.salt.server.Account.api.dto.ScoreRequest;
import com.salt.server.Account.api.dto.ScoreResponse;
import com.salt.server.Account.model.Account;
import com.salt.server.Account.model.Score;
import com.salt.server.Account.repository.AccountRepository;
import com.salt.server.Account.repository.ScoreRepository;
import com.salt.server.test.Test;
import com.salt.server.test.TestRepository;
import com.salt.server.test.TestService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ScoreService {

    private final ScoreRepository scoreRepository;
    private final AccountRepository accountRepository;
    private final TestService testService;

    public ScoreService(ScoreRepository scoreRepository, AccountRepository accountRepository, TestService testService) {
        this.scoreRepository = scoreRepository;
        this.accountRepository = accountRepository;
        this.testService = testService;
    }

    public ScoreListResponse getAllScoreById(UUID id) {
        List<Score> scores = scoreRepository.findAllByAccount_Id(id);
        return new ScoreListResponse( scores.stream().map(score -> new ScoreResponse(score.getId(), score.getTest().getName(),score.getScore())).collect(Collectors.toList()));
    }

    public ScoreResponse addScore(String id, ScoreRequest request) {
        Account account = accountRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new NoSuchElementException("Account not found"));
        Test test = testService.getTestByName(request.name());
        Score score = new Score();
        score.setAccount(account);
        score.setTest(test);
        score.setScore(request.score());
        score.setDescription(request.description());
        Score saveScore = scoreRepository.save(score);
        return new ScoreResponse(saveScore.getId(),saveScore.getTest().getName(), saveScore.getScore());
    }
}
