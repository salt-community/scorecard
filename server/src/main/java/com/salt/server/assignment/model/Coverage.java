package com.salt.server.assignment.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Coverage {
    @Id
    @UuidGenerator
    private UUID id;
    @Enumerated(EnumType.STRING)
    private Focus focus;
    private int percentage;
    @ManyToOne
    @JoinColumn(name = "assignment_id", nullable = false)
    private Assignment assignment;

    public static class CoverageBuilder {
        public CoverageBuilder focus(String focus) {
            this.focus = switch (focus) {
                case "frontend" -> Focus.frontend;
                case "backend" -> Focus.backend;
                case "charismatic" -> Focus.charismatic;
                case "teamwork" -> Focus.teamwork;
                case "design" -> Focus.design;
                default -> Focus.management;
            };
            return this;
        }
    }
}
