package se.salt.server2.domain.background.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "background")
public class BackgroundEntity {

    @Id
    @GeneratedValue
    UUID id;

    String firstName;

    String lastName;

    String githubUser;

    String emailAddress;

    @Enumerated(EnumType.STRING)
    BootcampCourse bootcampCourse;

}