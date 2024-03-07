package se.salt.server2.domain.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import se.salt.server2.domain.account.models.AccountEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {
    @Query("SELECT a FROM AccountEntity a JOIN FETCH a.background")
    List<AccountEntity> findAllWithBackgroundEntity();
}