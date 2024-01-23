package com.salt.server.Account.repository;

import com.salt.server.Account.model.Language;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface LanguageRepository extends ListCrudRepository<Language, UUID> {
}
