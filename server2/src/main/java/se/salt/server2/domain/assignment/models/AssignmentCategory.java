package se.salt.server2.domain.assignment.models;

public enum AssignmentCategory {
    BACKEND("Backend"),
    FRONTEND("Frontend");
    private final String value;

    AssignmentCategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
