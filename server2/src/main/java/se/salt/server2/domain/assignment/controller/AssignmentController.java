package se.salt.server2.domain.assignment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import se.salt.server2.domain.assignment.controller.dto.AssignmentRequest;
import se.salt.server2.domain.assignment.controller.dto.AssignmentResponse;
import se.salt.server2.domain.assignment.service.AssignmentService;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/assignments")
public class AssignmentController {
    private final AssignmentService assignmentService;

    @PostMapping
    @ResponseStatus(CREATED)
    public AssignmentResponse createAssignment(@RequestBody AssignmentRequest assignmentRequest) {
        return assignmentService.createAssignment(assignmentRequest);
    }
}
