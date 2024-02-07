package com.salt.server.v1.account.repository;

import com.salt.server.v1.account.model.Skill;
import com.salt.server.v1.account.model.UserDetail;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface SkillRepository extends ListCrudRepository<Skill, UUID> {
    List<Skill> findAllByUserDetail(UserDetail userDetail);
}
