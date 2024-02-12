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

    public static AssignmentCategory fromString(String text) {
        for (AssignmentCategory category : AssignmentCategory.values()) {
            if (category.value.equalsIgnoreCase(text)) {
                return category;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }
}
