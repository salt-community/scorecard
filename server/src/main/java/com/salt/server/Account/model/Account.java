package com.salt.server.Account.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;
@Setter
@Getter
@Entity
public class Account {
    @Id
    @UuidGenerator
    private UUID id;
    private String username;
    private String password = "saltdeveloper";
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne
    @JoinColumn(name = "user_detail_id", referencedColumnName = "id")
    private UserDetail userDetail;

    @OneToMany(mappedBy = "account")
    private List<Score> scores;
}
