package com.salt.server.Account.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@Entity
public class Skill {
    @Id
    @UuidGenerator
    private UUID id;
    private String skill;
    @ManyToOne
    @JoinColumn(name = "user_detail_id", nullable = false)
    private UserDetail userDetail;
}
