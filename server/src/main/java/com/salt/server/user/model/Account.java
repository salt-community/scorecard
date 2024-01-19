package com.salt.server.user.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;

@Entity
public class Account {
    @Id
    @UuidGenerator
    private String id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne
    @JoinColumn(name = "user_detail_id", referencedColumnName = "id")
    private UserDetail userDetail;

    @OneToMany(mappedBy = "account")
    private List<Score> scores;
}
