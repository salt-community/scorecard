package com.salt.server.Account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Language {
    @Id
    @UuidGenerator
    private UUID id;
    private String language;
    @Setter(AccessLevel.NONE)
    @Enumerated(EnumType.STRING)
    private Fluency fluency;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_detail_id", nullable = false)
    private UserDetail userDetail;

    public static class LanguageBuilder{
        public LanguageBuilder fluency(String fluency) {
            this.fluency = switch (fluency) {
                case "natives" -> Fluency.natives;
                case "fluent" -> Fluency.fluent;
                case "intermediate" -> Fluency.intermediate;
                default -> Fluency.beginner;
            };
            return this;
        }
    }

}
