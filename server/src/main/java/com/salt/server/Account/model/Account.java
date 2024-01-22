package com.salt.server.Account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @UuidGenerator
    private UUID id;
    private String username;
    @JsonIgnore
    @OneToOne(mappedBy = "account")
    private UserDetail userDetail;
    @JsonIgnore
    @OneToMany(mappedBy = "account")
    private List<Score> scores;
}
