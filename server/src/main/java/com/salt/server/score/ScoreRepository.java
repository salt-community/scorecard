package com.salt.server.score;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ScoreRepository extends ListCrudRepository<Score, UUID> {
    List<Score> findAllByAccount_Id(UUID id);
}
