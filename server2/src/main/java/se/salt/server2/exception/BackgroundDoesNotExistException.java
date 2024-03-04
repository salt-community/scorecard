package se.salt.server2.exception;

import java.util.UUID;

import static java.lang.String.format;

public class BackgroundDoesNotExistException extends RuntimeException {

    public BackgroundDoesNotExistException(UUID id) {
        super(format("Background with id: %s - does not exist.", id));
    }
}