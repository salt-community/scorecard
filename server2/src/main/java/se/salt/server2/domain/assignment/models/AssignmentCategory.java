package se.salt.server2.domain.assignment.models;

import se.salt.server2.exception.EnumDoesNotExist;

import java.util.EnumSet;

public enum AssignmentCategory {
    BACKEND("Backend"),
    FRONTEND("Frontend");
    private final String value;

    AssignmentCategory(String value) {
        this.value = value;
    }

    public static AssignmentCategory fromString(String text) {
        return EnumSet.allOf(AssignmentCategory.class)
                .stream()
                .filter(assignmentCategory -> assignmentCategory.value.equalsIgnoreCase(text))
                .findFirst()
                .orElseThrow(() -> new EnumDoesNotExist(text));
    }

    public String getValue() {
        return value;
    }
}