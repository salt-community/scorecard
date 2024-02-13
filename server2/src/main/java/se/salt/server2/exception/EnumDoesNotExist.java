package se.salt.server2.exception;

import static java.lang.String.format;

public class EnumDoesNotExist extends RuntimeException {

    public EnumDoesNotExist(String text) {
        super(format("No constant with text %s found.", text));
    }

}