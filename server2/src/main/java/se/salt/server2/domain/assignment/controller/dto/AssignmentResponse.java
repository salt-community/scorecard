package se.salt.server2.domain.assignment.controller.dto;

import java.util.UUID;

public record AssignmentResponse(UUID assignmentId, String title, int score, String description, String category) {
}
