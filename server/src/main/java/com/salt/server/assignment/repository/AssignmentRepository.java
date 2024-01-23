package com.salt.server.assignment.repository;

import com.salt.server.assignment.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, UUID> {
    Optional<Assignment> findByName(String name);
}
