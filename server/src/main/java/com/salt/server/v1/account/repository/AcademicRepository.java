package com.salt.server.v1.account.repository;

import com.salt.server.v1.account.model.Academic;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AcademicRepository extends ListCrudRepository<Academic, UUID> {
}
