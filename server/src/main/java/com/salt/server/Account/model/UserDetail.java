package com.salt.server.Account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;


@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserDetail {
    @Setter
    @Id
    @UuidGenerator
    private UUID id;
    @Setter
    private String name;
    @Setter
    private String education;
    @Setter
    private String nationality;
    @Setter
    private String skills;
    @Setter
    private String languages;
    @Enumerated(EnumType.STRING)
    private Bootcamp bootcamp;
    @Setter
    @JsonIgnore
    @OneToOne(mappedBy = "userDetail")
    private Social social;
    @Setter
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;


    public void setBootcamp(String bootcamp) {
        this.bootcamp = switch (bootcamp) {
            case "java" -> Bootcamp.JAVA;
            case "javascript" -> Bootcamp.JAVASCRIPT;
            default -> Bootcamp.DOTNET;
        };
    }
}
