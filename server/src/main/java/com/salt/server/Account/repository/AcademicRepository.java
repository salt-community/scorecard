package com.salt.server.Account.repository;

import com.salt.server.Account.model.Academic;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface AcademicRepository extends ListCrudRepository<Academic, UUID> {
}
