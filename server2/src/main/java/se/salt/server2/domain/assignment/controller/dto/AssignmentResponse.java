package se.salt.server2.domain.assignment.controller.dto;

import lombok.Builder;


@Builder(toBuilder = true)
public record AssignmentResponse(String title, int score, String description, String category) {
}
