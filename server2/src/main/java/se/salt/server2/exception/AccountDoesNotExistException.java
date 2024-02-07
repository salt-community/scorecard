package se.salt.server2.exception;

import java.util.UUID;

import static java.lang.String.format;

public class AccountDoesNotExistException extends RuntimeException {
    public AccountDoesNotExistException(UUID id) {
        super(format("Account with id: %s - does not exist.", id));
    }
}
