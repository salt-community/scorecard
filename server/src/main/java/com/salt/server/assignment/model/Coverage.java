package com.salt.server.assignment.model;

import com.salt.server.Account.model.Bootcamp;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
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
