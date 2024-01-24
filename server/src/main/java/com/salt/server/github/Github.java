package com.salt.server.github;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salt.server.Account.model.Social;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Github {
    @Id
    @UuidGenerator
    private UUID id;
    @Setter(AccessLevel.NONE)
    private String url;
    @Setter(AccessLevel.NONE)
    private String pictureUrl;
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "social_id", referencedColumnName = "id")
    private Social social;
    @OneToMany(mappedBy = "github")
    private List<Project> project;

    public void setUrl(String url) {
        this.url = String.format("https://github.com/%s", url);
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = String.format("https://github.com/%s.png", pictureUrl);
    }

}
