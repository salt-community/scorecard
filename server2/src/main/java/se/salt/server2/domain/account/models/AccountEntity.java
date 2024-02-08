package se.salt.server2.domain.account.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

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
}