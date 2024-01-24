package com.salt.server.Account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@Setter
@Entity
public class Nationality {
    @Id
    @UuidGenerator
    private UUID id;
    private String nationality;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userDetail_id", nullable = false)
    private UserDetail userDetail;
}
