package com.salt.server.Account.model;

import com.salt.server.github.Github;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@Setter
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
