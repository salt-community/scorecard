package se.salt.server2.domain.account.models;

import jakarta.persistence.*;
import lombok.*;
import se.salt.server2.domain.background.models.BackgroundEntity;

import java.util.UUID;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "account")
public class AccountEntity {
    @Id
    @GeneratedValue
    UUID id;

    @Setter
    String emailAddress;

    @OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
    private BackgroundEntity background;
}