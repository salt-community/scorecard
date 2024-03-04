package se.salt.server2.domain.developer.models;

import se.salt.server2.exception.EnumDoesNotExist;

import java.util.EnumSet;

public enum Bootcamp {

    JAVASCRIPT("Javascript"),
    JAVA("Java"),
    DOT_NET(".Net");

    private final String value;

    Bootcamp(String value) {
        this.value = value;
    }

    public static Bootcamp fromString(String text) {
        return EnumSet.allOf(Bootcamp.class)
                .stream()
                .filter(bootcampCourse -> bootcampCourse.value.equalsIgnoreCase(text))
                .findFirst()
                .orElseThrow(() -> new EnumDoesNotExist(text));
    }

    public String getValue() {
        return value;
    }
}
