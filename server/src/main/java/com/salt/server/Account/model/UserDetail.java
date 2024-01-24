package com.salt.server.Account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;


@Data
@Entity
public class UserDetail {
    @Id
    @UuidGenerator
    private UUID id;
    private String name;
    private String introduction;
    @Setter(AccessLevel.NONE)
    @Enumerated(EnumType.STRING)
    private Bootcamp bootcamp;
    @JsonIgnore
    @OneToMany(mappedBy = "userDetail")
    private List<Nationality> nationality;
    @JsonIgnore
    @OneToOne(mappedBy = "userDetail")
    private Academic academic;
    @JsonIgnore
    @OneToMany(mappedBy = "userDetail")
    private List<Skill> skills;
    @JsonIgnore
    @OneToMany(mappedBy = "userDetail")
    private List<Language> languages;
    @JsonIgnore
    @OneToOne(mappedBy = "userDetail")
    private Social social;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;


    public void setBootcamp(String bootcamp) {
        this.bootcamp = switch (bootcamp) {
            case "java" -> Bootcamp.java;
            case "javascript" -> Bootcamp.javascript;
            default -> Bootcamp.dotnet;
        };
    }
}
