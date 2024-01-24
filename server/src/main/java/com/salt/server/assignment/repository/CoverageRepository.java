package com.salt.server.assignment.repository;

import com.salt.server.assignment.model.Coverage;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CoverageRepository extends ListCrudRepository<Coverage, UUID> {
}
