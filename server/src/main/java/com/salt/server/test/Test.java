package com.salt.server.test;

import com.salt.server.Account.model.Score;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;

@Setter
@Getter
@Entity
public class Test {
    @Id
    @UuidGenerator
    private String id;
    private String name;
    private String type;
    @OneToMany(mappedBy = "test")
    private List<Score> scores;
}
