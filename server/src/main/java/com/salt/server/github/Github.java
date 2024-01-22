package com.salt.server.github;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Getter
@Entity
public class Github {
    @Setter
    @Id
    @UuidGenerator
    private UUID id;
    private String url;
    private String pictureUrl;
    @Setter
    private int commit;
    @Setter
    private int issue;

    public void setUrl(String url) {
        this.url = String.format("https://github.com/%s", url);
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = String.format("https://github.com/%s.png", pictureUrl);
    }

}
