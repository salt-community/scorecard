package se.salt.server2.domain.developer.models;

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
@Setter
@Entity
@Table(name = "developer")
public class DeveloperEntity {

    @Id
    @GeneratedValue
    UUID id;

    String firstName;

    String LastName;

    String emailAddress;
}
