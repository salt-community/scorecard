package com.salt.server.v1.github.repository;

import com.salt.server.v1.github.model.Project;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProjectRepository extends ListCrudRepository<Project, UUID> {
}
