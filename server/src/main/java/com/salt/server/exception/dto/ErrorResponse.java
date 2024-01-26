package com.salt.server.exception.dto;

import java.util.List;

public record ErrorResponse(List<Error> errors) {
}
