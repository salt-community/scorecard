package se.salt.server2.domain.associations.account_assignment;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import se.salt.server2.domain.account.models.AccountEntity;
import se.salt.server2.domain.assignment.models.AssignmentEntity;

import java.util.UUID;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "account_assignment")
public class AccountAssignmentMapping {
    // This is optional but could be useful in the future
    @Id
    @UuidGenerator
    @Column
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private AccountEntity account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignment_id")
    private AssignmentEntity assignment;

    private int score;
}
