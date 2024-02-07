package com.salt.server.v1.account.repository;

import com.salt.server.v1.account.model.Account;
import com.salt.server.v1.account.model.Role;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends ListCrudRepository<Account, UUID> {
    List<Account> findAllByRole(Role role);
    List<Account> findAllByRoleNot(Role role);
    Optional<Account> findByEmailAndRole(String email, Role role);
}
