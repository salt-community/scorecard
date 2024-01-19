package com.salt.server.github;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GithubRepository extends ListCrudRepository<Github, UUID> {
}
