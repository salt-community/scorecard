package com.salt.server.github;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
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
    private String url;
    private String pictureUrl;
    private int commit;
    private int issue;

}
