package se.salt.server2.exception;

import java.util.UUID;

import static java.lang.String.format;

public class DeveloperDoesNotExistException extends RuntimeException{

    public DeveloperDoesNotExistException(UUID id) {
        super(format("Developer with id: %s - does not exist.", id));
    }
}
