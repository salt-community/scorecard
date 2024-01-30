package com.salt.server.Account.repository;

import com.salt.server.Account.model.Nationality;
import com.salt.server.Account.model.Skill;
import com.salt.server.Account.model.UserDetail;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NationalityRepository extends ListCrudRepository<Nationality, UUID> {
    List<Nationality> findAllByUserDetail(UserDetail userDetail);

}
