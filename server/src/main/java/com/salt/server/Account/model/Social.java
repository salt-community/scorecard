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
@Entity
public class Social {
    @Setter
    @Id
    @UuidGenerator
    private UUID id;
    private String linkedInUrl;
    private String codewarsUrl;
    @Setter
    @OneToOne
    @JoinColumn(name = "github_id", referencedColumnName = "id")
    private Github githubId;

    public void setLinkedInUrl (String linkedInUrl) {
        this.linkedInUrl = String.format("https://www.linkedin.com/in/%s", linkedInUrl);
    }

    public void setCodewarsUrl(String codewarsUrl) {
        this.codewarsUrl = String.format("https://www.codewars.com/users/%s", codewarsUrl);
    }

}
