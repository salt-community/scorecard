package com.salt.server.github;

import com.salt.server.user.model.Social;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
    @OneToOne
    private Social social;
}
