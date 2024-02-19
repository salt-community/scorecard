package se.salt.server2.domain.assignment.controller.dto;

import lombok.Builder;

import java.util.UUID;


@Builder(toBuilder = true)
public record AssignmentResponse(UUID assignmentId, UUID developerId, String title, int score, String description,
                                 String category) {
}