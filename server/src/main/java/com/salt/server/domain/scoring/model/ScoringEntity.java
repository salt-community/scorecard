package com.salt.server.domain.scoring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salt.server.v1.account.model.Account;
import com.salt.server.v1.assignment.model.Assignment;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class ScoringEntity {


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "assignment_id", nullable = false)
    private Assignment assignment;

}
