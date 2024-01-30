package com.salt.server.github.repository;

import com.salt.server.Account.model.Social;
import com.salt.server.github.model.Github;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GithubRepository extends ListCrudRepository<Github, UUID> {
    Github findBySocial(Social social);
}
