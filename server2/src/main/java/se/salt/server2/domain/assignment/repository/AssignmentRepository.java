package se.salt.server2.domain.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.salt.server2.domain.assignment.models.AssignmentEntity;

import java.util.UUID;

@Repository
public interface AssignmentRepository extends JpaRepository<AssignmentEntity, UUID> {
}