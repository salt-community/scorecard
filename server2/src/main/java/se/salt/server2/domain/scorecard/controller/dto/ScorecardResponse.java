package se.salt.server2.domain.scorecard.controller.dto;

import lombok.Builder;
import se.salt.server2.domain.assignment.controller.dto.AssignmentResponse;

import java.util.List;

@Builder
public record ScorecardResponse(List<AssignmentResponse> assignmentResponseList) {
}
