package se.salt.server2.domain.developer.models;

import jakarta.persistence.*;
import lombok.*;
import se.salt.server2.domain.assignment.models.AssignmentEntity;

import java.util.List;
import java.util.UUID;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "developer")
public class DeveloperEntity {

    @Id
    @GeneratedValue
    UUID id;

    UUID accountId;

    String firstName;

    String LastName;

    String emailAddress;

    @Enumerated(EnumType.STRING)
    BootcampCourse bootcampCourse;

    @OneToMany(mappedBy = "developer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AssignmentEntity> assignments;
}
