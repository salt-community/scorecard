package com.salt.server.v1.score;

import com.salt.server.v1.account.model.Account;
import com.salt.server.v1.assignment.model.Assignment;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ScoreRepository extends ListCrudRepository<Score, UUID> {
    List<Score> findAllByAccount_Id(UUID id);
    Score findByAccountAndAssignment(Account account, Assignment assignment);
}
