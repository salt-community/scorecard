package com.salt.server.Account.model;

import jakarta.persistence.*;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Setter
@Entity
public class UserDetail {
    @Id
    @UuidGenerator
    private UUID id;
    private String name;
//    private String education = "";
//    private List<String> nationality;
//    private List<String> skills;
    @Enumerated(EnumType.STRING)
    private Bootcamp bootcamp;
    @OneToOne
    @JoinColumn(name = "social_id", referencedColumnName = "id")
    private Social social;
    @OneToOne
    private Account account;
}
