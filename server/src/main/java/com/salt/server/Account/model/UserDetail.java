package com.salt.server.Account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserDetail {
    @Id
    @UuidGenerator
    private UUID id;
    private String name;
    private String introduction;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Bootcamp bootcamp;
    @Setter
    @JsonIgnore
    @OneToOne(mappedBy = "userDetail", cascade = CascadeType.REMOVE)
    private Academic academic;
    @Setter
    @JsonIgnore
    @OneToOne(mappedBy = "userDetail", cascade = CascadeType.REMOVE)
    private Social social;
    @JsonIgnore
    @OneToOne(mappedBy = "userDetail", cascade = CascadeType.REMOVE)
    private Account account;
    @Setter
    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "userDetail", cascade = CascadeType.REMOVE)
    private List<Nationality> nationality = new ArrayList<>();
    @Setter
    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "userDetail", cascade = CascadeType.REMOVE)
    private List<Skill> skills = new ArrayList<>();
    @Setter
    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "userDetail", cascade = CascadeType.REMOVE)
    private List<Language> languages = new ArrayList<>();


    public static class UserDetailBuilder{
        public UserDetailBuilder bootcamp(String bootcamp) {
            this.bootcamp = switch (bootcamp) {
                case "java" -> Bootcamp.java;
                case "javascript" -> Bootcamp.javascript;
                default -> Bootcamp.dotnet;
            };
            return this;
        }
    }

    public void addNationality(Nationality nationality){
        this.nationality.add(nationality);
    }

    public void addSkill(Skill skill) {
        this.skills.add(skill);
    }

    public void addLanguage(Language language) {
        this.languages.add(language);
    }
}
