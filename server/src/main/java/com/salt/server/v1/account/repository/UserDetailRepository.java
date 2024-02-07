package com.salt.server.v1.account.repository;

import com.salt.server.v1.account.model.Account;
import com.salt.server.v1.account.model.UserDetail;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDetailRepository extends ListCrudRepository<UserDetail, UUID> {
    Optional<UserDetail> findByAccount(Account account);
}
