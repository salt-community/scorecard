package com.salt.server.v1.assignment.repository;

import com.salt.server.v1.assignment.model.Assignment;
import com.salt.server.v1.assignment.model.Coverage;
import com.salt.server.v1.assignment.model.Focus;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CoverageRepository extends ListCrudRepository<Coverage, UUID> {
    Coverage findByAssignmentAndFocus(Assignment assignment, Focus focus);
}
