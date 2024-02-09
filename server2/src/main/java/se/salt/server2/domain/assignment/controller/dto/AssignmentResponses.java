package se.salt.server2.domain.assignment.controller.dto;

import lombok.Builder;

import java.util.List;

@Builder(toBuilder = true)
public record AssignmentResponses(List<AssignmentResponse> assignmentResponseList) {
}