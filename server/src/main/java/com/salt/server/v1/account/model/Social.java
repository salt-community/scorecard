package com.salt.server.v1.account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salt.server.v1.github.model.Github;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Social {
    @Id
    @UuidGenerator
    private UUID id;
    private String linkedInUrl;
    private String codewarsUrl;
    @Setter
    @JsonIgnore
    @OneToOne(mappedBy = "social", cascade = CascadeType.ALL)
    private Github githubId;
    @Setter
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userDetail_id", referencedColumnName = "id")
    private UserDetail userDetail;

    public static class SocialBuilder{
        public SocialBuilder codewarsUrl(String codewarsUsername) {
            this.codewarsUrl = String.format("https://www.codewars.com/users/%s", codewarsUsername);
            return this;
        }

        public SocialBuilder linkedInUrl(String linkedInUsername) {
            this.linkedInUrl = String.format("https://www.linkedin.com/in/%s", linkedInUsername);
            return this;
        }
    }




}
