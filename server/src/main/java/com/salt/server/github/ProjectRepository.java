package com.salt.server.github;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProjectRepository extends ListCrudRepository<Project, UUID> {
    List<Project> findAllByGithubId(UUID uuid);
}
