package com.salt.server.Account.model;

import com.salt.server.test.Test;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
public class Score {
    @Id
    @UuidGenerator
    private UUID id;
    private int score;
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
    @ManyToOne
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;
}
