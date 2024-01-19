package com.salt.server.github;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

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
