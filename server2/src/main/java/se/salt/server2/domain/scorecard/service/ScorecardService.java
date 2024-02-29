package se.salt.server2.domain.scorecard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.salt.server2.domain.assignment.service.AssignmentService;
import se.salt.server2.domain.scorecard.controller.dto.ScorecardResponse;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ScorecardService {

    private final AssignmentService assignmentService;

    public ScorecardResponse getScorecardByDeveloperId(UUID id) {
        return ScorecardResponse.builder()
                .assignmentResponses(assignmentService.getAssignmentsByDeveloperId(id))
                .build();
    }
}
