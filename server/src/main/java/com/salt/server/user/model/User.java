package com.salt.server.user.model;

import jakarta.persistence.*;
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
    @OneToOne
    @JoinColumn(name = "social_id", referencedColumnName = "id")
    private UserDetail userDetail;
}
