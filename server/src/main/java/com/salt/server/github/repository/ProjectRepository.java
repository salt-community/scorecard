package com.salt.server.github.repository;

import com.salt.server.github.model.Project;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProjectRepository extends ListCrudRepository<Project, UUID> {
}
