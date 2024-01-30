package com.salt.server.Account.repository;

import com.salt.server.Account.model.Language;
import com.salt.server.Account.model.Skill;
import com.salt.server.Account.model.UserDetail;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LanguageRepository extends ListCrudRepository<Language, UUID> {
    List<Language> findAllByUserDetail(UserDetail userDetail);

}
