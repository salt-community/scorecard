package com.salt.server.Account.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@Setter
@Entity
public class Language {
    @Id
    @UuidGenerator
    private UUID id;
    private String language;
    @Setter(AccessLevel.NONE)
    @Enumerated(EnumType.STRING)
    private Fluency fluency;
    @ManyToOne
    @JoinColumn(name = "user_detail_id", nullable = false)
    private UserDetail userDetail;

    public void setFluency(String fluency) {
        this.fluency = switch (fluency) {
            case "natives" -> Fluency.natives;
            case "fluent" -> Fluency.fluent;
            case "intermediate" -> Fluency.intermediate;
            default -> Fluency.beginner;
        };
    }
}
