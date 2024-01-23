package com.salt.server.score;

import com.salt.server.Account.model.Account;
import com.salt.server.assignment.model.Assignment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Score {
    @Id
    @UuidGenerator
    private UUID id;
    private int score;
    @Column(columnDefinition = "text")
    private String description;
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
    @ManyToOne
    @JoinColumn(name = "assignment_id", nullable = false)
    private Assignment assignment;
}
