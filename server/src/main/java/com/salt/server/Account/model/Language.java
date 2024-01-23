package com.salt.server.Account.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
public class Language {
    @Id
    @UuidGenerator
    private UUID id;
    private String language;
    @Enumerated(EnumType.STRING)
    private Fluency fluency;
    @ManyToOne
    @JoinColumn(name = "user_detail_id", nullable = false)
    private UserDetail userDetail;
}
