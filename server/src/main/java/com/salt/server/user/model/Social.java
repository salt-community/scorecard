package com.salt.server.user.model;

import com.salt.server.github.Github;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
public class Social {
    @Id
    @UuidGenerator
    private UUID id;
    private String linkedInUrl;
    private String codewarsUrl;
    @OneToOne
    @JoinColumn(name = "github_id", referencedColumnName = "id")
    private Github githubId;

}
