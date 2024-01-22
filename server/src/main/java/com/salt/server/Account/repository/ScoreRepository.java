package com.salt.server.Account.repository;

import com.salt.server.Account.model.Score;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ScoreRepository extends ListCrudRepository<Score, UUID> {
    List<Score> findAllByAccount_Id(UUID id);
}
