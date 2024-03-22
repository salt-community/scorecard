package se.salt.server2.domain.associations.account_role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRoleRepository extends JpaRepository<AccountRoleMapping, UUID> {
    List<AccountRoleMapping> findAllByRoleId(Integer id);

    AccountRoleMapping findByRoleIdAndAccountId(Integer roleId, UUID accountId);


}