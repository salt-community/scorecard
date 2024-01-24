package com.salt.server.Account.repository;

import com.salt.server.Account.model.Skill;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface SkillRepository extends ListCrudRepository<Skill, UUID> {
}
