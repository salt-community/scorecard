package se.salt.server2.domain.background.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.salt.server2.domain.background.models.BackgroundEntity;

import java.util.UUID;

@Repository
public interface BackgroundRepository extends JpaRepository<BackgroundEntity, UUID> {
}
