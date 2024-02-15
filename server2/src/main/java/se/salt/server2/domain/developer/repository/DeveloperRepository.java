package se.salt.server2.domain.developer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.salt.server2.domain.developer.models.DeveloperEntity;

import java.util.UUID;

@Repository
public interface DeveloperRepository extends JpaRepository<DeveloperEntity, UUID> {
}
