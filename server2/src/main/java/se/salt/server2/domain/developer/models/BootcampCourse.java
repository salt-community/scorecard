package se.salt.server2.domain.developer.models;

public enum BootcampCourse {

    JAVASCRIPT("Javascript"),
    JAVA("Java"),
    DOT_NET(".Net");

    private final String value;

    BootcampCourse(String value) {
        this.value = value;
    }
}
