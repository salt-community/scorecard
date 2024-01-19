package com.salt.server.Account.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
public class UserDetail {
    @Id
    @UuidGenerator
    private UUID id;
    private String name;
    private String education = "";
    @Enumerated(EnumType.STRING)
    private Bootcamp bootcamp;
    @OneToOne
    @JoinColumn(name = "social_id", referencedColumnName = "id")
    private Social social;
    @OneToOne
    private Account account;
}
