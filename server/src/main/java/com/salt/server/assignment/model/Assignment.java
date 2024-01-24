package com.salt.server.assignment.model;

import com.salt.server.Account.model.Bootcamp;
import com.salt.server.score.Score;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Assignment {
    @Id
    @UuidGenerator
    private UUID id;
    private String name;
    @Setter(AccessLevel.NONE)
    @Enumerated(EnumType.STRING)
    private Type type;
    @OneToMany(mappedBy = "assignment")
    private List<Score> scores;
    @OneToMany(mappedBy = "assignment")
    private List<Coverage> coverages;

    public void setType(String type) {
        this.type = switch (type) {
            case "communication" -> Type.communication;
            case "planning" -> Type.planning;
            default -> Type.coding;
        };
    }
}
