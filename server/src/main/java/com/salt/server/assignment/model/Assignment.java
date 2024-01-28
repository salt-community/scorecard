package com.salt.server.assignment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salt.server.score.Score;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Assignment {
    @Id
    @UuidGenerator
    private UUID id;
    private String name;
    @Setter(AccessLevel.NONE)
    @Enumerated(EnumType.STRING)
    private Type type;
    @JsonIgnore
    @OneToMany(mappedBy = "assignment")
    private List<Score> scores = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "assignment")
    private List<Coverage> coverages = new ArrayList<>();

    public void setType(String type) {
        this.type = switch (type) {
            case "communication" -> Type.communication;
            case "planning" -> Type.planning;
            default -> Type.coding;
        };
    }

    public void addScore(Score score) {
        this.scores.add(score);
    }

    public void addCoverage(Coverage coverage) {
        this.coverages.add(coverage);
    }
}
