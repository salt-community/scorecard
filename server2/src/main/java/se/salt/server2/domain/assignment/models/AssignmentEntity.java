package se.salt.server2.domain.assignment.models;

import jakarta.persistence.*;
import lombok.*;
import se.salt.server2.domain.developer.models.DeveloperEntity;

import java.util.UUID;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "assignment")
public class AssignmentEntity {
    @Id
    @GeneratedValue
    UUID id;
    UUID accountId;
    String title;
    int score;
    String description;
    @Enumerated(EnumType.STRING)
    AssignmentCategory category;

    @ManyToOne
    @JoinColumn(name = "developer_id")
    private DeveloperEntity developer;
}
