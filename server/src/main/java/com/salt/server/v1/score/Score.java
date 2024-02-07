package com.salt.server.v1.score;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salt.server.v1.account.model.Account;
import com.salt.server.v1.assignment.model.Assignment;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@Setter
@Entity
public class Score {
    @Id
    @UuidGenerator
    private UUID id;
    private int score;
    @Column(columnDefinition = "text")
    private String description;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "assignment_id", nullable = false)
    private Assignment assignment;
}
