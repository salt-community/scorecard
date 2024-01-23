package com.salt.server.Account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salt.server.github.Github;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Social {
    @Setter
    @Id
    @UuidGenerator
    private UUID id;
    @Setter
    private String linkedInUrl;
    private String codewarsUrl;
    @Setter
    @JsonIgnore
    @OneToOne(mappedBy = "social")
    private Github githubId;
    @Setter
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "userDetail_id", referencedColumnName = "id")
    private UserDetail userDetail;


    public void setCodewarsUrl(String codewarsUrl) {
        this.codewarsUrl = String.format("https://www.codewars.com/users/%s", codewarsUrl);
    }

}
