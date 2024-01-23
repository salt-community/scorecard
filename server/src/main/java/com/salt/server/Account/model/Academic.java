package com.salt.server.Account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
public class Academic {
    @Id
    @UuidGenerator
    private UUID id;
    @Enumerated(EnumType.STRING)
    private Degree degree;
    private int yearStudied;
    private String school;
    @Setter
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "userDetail_id", referencedColumnName = "id")
    private UserDetail userDetail;
}
