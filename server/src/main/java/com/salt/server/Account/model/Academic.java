package com.salt.server.Account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@Entity
public class Academic {
    @Id
    @UuidGenerator
    private UUID id;
    @Enumerated(EnumType.STRING)
    private Degree degree;
    private String major;
    private String startDate;
    private String endDate;
    private String school;
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "userDetail_id", referencedColumnName = "id")
    private UserDetail userDetail;
}
