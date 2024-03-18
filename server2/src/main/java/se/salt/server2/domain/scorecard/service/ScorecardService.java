package se.salt.server2.domain.scorecard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.salt.server2.domain.assignment.service.AssignmentService;
import se.salt.server2.domain.scorecard.controller.dto.ScorecardResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ScorecardService {

    private final AssignmentService assignmentService;

    public ScorecardResponse getScorecardByAccountId(UUID id) {
        return ScorecardResponse.builder()
                .assignmentResponseList(new ArrayList<>(assignmentService.getAssignmentsByAccountId(id)))
                .build();
    }
}