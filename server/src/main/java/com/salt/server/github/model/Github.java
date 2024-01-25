package com.salt.server.github.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salt.server.Account.model.Social;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Github {
    @Id
    @UuidGenerator
    private UUID id;
    private String url;
    private String pictureUrl;
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "social_id", referencedColumnName = "id")
    private Social social;
    @Setter
    @Builder.Default
    @OneToMany(mappedBy = "github")
    private List<Project> projects = new ArrayList<>();


    public static class GithubBuilder {
        public GithubBuilder url(String url) {
            this.url = String.format("https://github.com/%s", url);
            return this;
        }

        public GithubBuilder pictureUrl(String pictureUrl) {
            this.pictureUrl = String.format("https://github.com/%s.png", pictureUrl);
            return this;
        }
    }

    public void addProject(Project project) {
        this.projects.add(project);
    }

}
