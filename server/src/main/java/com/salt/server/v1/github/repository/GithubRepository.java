package com.salt.server.v1.github.repository;

import com.salt.server.v1.account.model.Social;
import com.salt.server.v1.github.model.Github;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GithubRepository extends ListCrudRepository<Github, UUID> {
    Github findBySocial(Social social);
}
