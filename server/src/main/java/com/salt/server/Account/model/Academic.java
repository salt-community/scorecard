package com.salt.server.Account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
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
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userDetail_id", referencedColumnName = "id")
    private UserDetail userDetail;

    public void setDegree(String degree) {
        this.degree = switch (degree) {
            case "bachelor" -> Degree.bachelor;
            case "doctoral" -> Degree.doctoral;
            case "master" -> Degree.master;
            case "vocationaldiploma" -> Degree.vocationaldiploma;
            default -> Degree.highschool;
        };
    }

}
