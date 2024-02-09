package se.salt.server2.domain.assignment.controller.dto;

import lombok.Builder;

import java.util.UUID;

@Builder(toBuilder = true)
public record AssignmentRequest(UUID accountId, String title, int score, String description, String category) {
}
