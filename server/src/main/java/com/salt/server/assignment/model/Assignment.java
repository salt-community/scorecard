package com.salt.server.assignment.model;

import com.salt.server.score.Score;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Entity
public class Assignment {
    @Id
    @UuidGenerator
    private UUID id;
    private String name;
    private Type type;
    @OneToMany(mappedBy = "assignment")
    private List<Score> scores;
    @OneToMany(mappedBy = "assignment")
    private List<Coverage> coverages;
}
