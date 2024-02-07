package com.salt.server.v1.account.repository;

import com.salt.server.v1.account.model.Social;
import com.salt.server.v1.account.model.UserDetail;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SocialRepository extends ListCrudRepository<Social, UUID> {
    Social findByUserDetail(UserDetail userDetail);
}
