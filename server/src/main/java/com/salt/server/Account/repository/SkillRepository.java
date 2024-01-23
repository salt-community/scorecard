package com.salt.server.Account.repository;

import com.salt.server.Account.model.Skill;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface SkillRepository extends ListCrudRepository<Skill, UUID> {
}
