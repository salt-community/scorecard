package com.salt.server.user.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;

@Entity
public class User {
    @Id
    @UuidGenerator
    private String id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
