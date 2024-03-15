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

    public List<ScorecardResponse> getAllScorecards() {
        List<ScorecardResponse> wallets = new ArrayList<>();

        ScorecardResponse.builder()
                .assignmentResponses(assignmentService.getAllAssignments())
                .build();

        return wallets;
    }

    public ScorecardResponse getScorecardByAccountId(UUID id) {
        return ScorecardResponse.builder()
                .assignmentResponses(assignmentService.getAssignmentsByAccountId(id))
                .build();
    }
}
