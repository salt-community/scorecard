package se.salt.server2.domain.background.models;

import se.salt.server2.exception.EnumDoesNotExist;

import java.util.EnumSet;

public enum BootcampCourse {

    JAVASCRIPT("Javascript"),
    JAVA("Java"),
    DOT_NET(".Net");

    private final String value;

    BootcampCourse(String value) {
        this.value = value;
    }

    public static BootcampCourse fromString(String text) {
        return EnumSet.allOf(BootcampCourse.class)
                .stream()
                .filter(bootcampCourse -> bootcampCourse.value.equalsIgnoreCase(text))
                .findFirst()
                .orElseThrow(() -> new EnumDoesNotExist(text));
    }

    public String getValue() {
        return value;
    }
}
