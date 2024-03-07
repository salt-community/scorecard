package se.salt.server2.domain.associations.account_assignment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountAssignmentRepository extends JpaRepository<AccountAssignmentMapping, UUID> {
    List<AccountAssignmentMapping> findAllByAccountId(UUID accountId);
}