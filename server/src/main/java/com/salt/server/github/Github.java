package com.salt.server.github;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salt.server.Account.model.Social;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Github {
    @Setter
    @Id
    @UuidGenerator
    private UUID id;
    private String url;
    private String pictureUrl;
    @Setter
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "social_id", referencedColumnName = "id")
    private Social social;
    @OneToMany(mappedBy = "github")
    private List<Project> projectList;

    public void setUrl(String url) {
        this.url = String.format("https://github.com/%s", url);
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = String.format("%s.png", pictureUrl);
    }

}
