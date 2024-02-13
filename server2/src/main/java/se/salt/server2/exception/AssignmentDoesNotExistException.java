package se.salt.server2.exception;

import java.util.UUID;

import static java.lang.String.format;

public class AssignmentDoesNotExistException extends RuntimeException {
    public AssignmentDoesNotExistException(UUID id) {
        super(format("Assignment with id: %s - does not exist.", id));
    }
}