package com.salt.server.v1.github.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salt.server.v1.account.model.Social;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
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
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "social_id", referencedColumnName = "id")
    private Social social;
    @JsonIgnore
    @OneToMany(mappedBy = "github", cascade = CascadeType.REMOVE)
    private List<Project> projects = new ArrayList<>();

    public void setUrl(String url) {
        this.url = String.format("https://github.com/%s", url);
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = String.format("https://github.com/%s.png", pictureUrl);
    }

    public void addProject(Project project) {
        this.projects.add(project);
    }

}
