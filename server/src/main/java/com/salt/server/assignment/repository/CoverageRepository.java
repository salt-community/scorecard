package com.salt.server.assignment.repository;

import com.salt.server.assignment.model.Coverage;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface CoverageRepository extends ListCrudRepository<Coverage, UUID> {
}
