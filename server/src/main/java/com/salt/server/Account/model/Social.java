package com.salt.server.Account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salt.server.github.Github;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@Setter
@Entity
public class Social {
    @Id
    @UuidGenerator
    private UUID id;
    @Setter(AccessLevel.NONE)
    private String linkedInUrl;
    @Setter(AccessLevel.NONE)
    private String codewarsUrl;
    @JsonIgnore
    @OneToOne(mappedBy = "social")
    private Github githubId;
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "userDetail_id", referencedColumnName = "id")
    private UserDetail userDetail;


    public void setCodewarsUrl(String codewarsUsername) {
        this.codewarsUrl = String.format("https://www.codewars.com/users/%s", codewarsUsername);
    }

    public void setLinkedInUrl(String linkedInUsername) {
        this.linkedInUrl = String.format("https://www.linkedin.com/in/%s", linkedInUsername);
    }

}
