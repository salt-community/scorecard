package se.salt.server2.domain.background.models;

import jakarta.persistence.*;
import lombok.*;
import se.salt.server2.domain.account.models.AccountEntity;
import se.salt.server2.domain.developer.models.Bootcamp;

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

    @Enumerated(EnumType.STRING)
    Bootcamp bootcamp;

    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private AccountEntity account;
}