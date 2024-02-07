package com.salt.server.v1.account.repository;

import com.salt.server.v1.account.model.Language;
import com.salt.server.v1.account.model.UserDetail;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LanguageRepository extends ListCrudRepository<Language, UUID> {
    List<Language> findAllByUserDetail(UserDetail userDetail);

}
