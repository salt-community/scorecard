package com.salt.server.exception.dto;

import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public record Error(UUID errorId, String message, String code, Map<String, ?> additionalContext) {

    public Error(String message, HttpStatus status) {
        this(UUID.randomUUID(), message, status.name(), Map.of());
    }

    public Error(String message, HttpStatus status, Map<String, List<String>> additionalContext) {
        this(UUID.randomUUID(), message, status.name(), additionalContext);
    }
}