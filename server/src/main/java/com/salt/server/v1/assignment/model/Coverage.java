package com.salt.server.v1.assignment.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@Setter
@Entity
public class Coverage {
    @Id
    @UuidGenerator
    private UUID id;
    @Setter(AccessLevel.NONE)
    @Enumerated(EnumType.STRING)
    private Focus focus;
    private int percentage;
    @ManyToOne
    @JoinColumn(name = "assignment_id", nullable = false)
    private Assignment assignment;


        public void setFocus(String focus) {
            this.focus = switch (focus) {
                case "frontend" -> Focus.frontend;
                case "backend" -> Focus.backend;
                case "charismatic" -> Focus.charismatic;
                case "teamwork" -> Focus.teamwork;
                case "design" -> Focus.design;
                default -> Focus.management;
            };
    }
}
