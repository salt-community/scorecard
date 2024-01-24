package com.salt.server.Account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salt.server.score.Score;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Account {
    @Id
    @UuidGenerator
    private UUID id;
    private String email;
    @JsonIgnore
    @OneToOne(mappedBy = "account")
    private UserDetail userDetail;
    @JsonIgnore
    @OneToMany(mappedBy = "account")
    private List<Score> scores;
}
