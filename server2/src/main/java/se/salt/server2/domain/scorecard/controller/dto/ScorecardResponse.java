package se.salt.server2.domain.scorecard.controller.dto;

import lombok.Builder;
import se.salt.server2.domain.assignment.controller.dto.AssignmentResponses;

@Builder
public record ScorecardResponse(AssignmentResponses assignmentResponses) {
}
