package com.salt.server.assignment.repository;

import com.salt.server.assignment.model.Coverage;
import com.salt.server.assignment.model.Focus;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CoverageRepository extends ListCrudRepository<Coverage, UUID> {
    Coverage findByAssignment_IdAndFocus(UUID id, Focus focus);
}
