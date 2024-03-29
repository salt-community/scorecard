package se.salt.server2.domain.assignment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import se.salt.server2.domain.assignment.controller.dto.AssignmentRequest;
import se.salt.server2.domain.assignment.controller.dto.AssignmentResponse;
import se.salt.server2.domain.assignment.controller.dto.AssignmentResponses;
import se.salt.server2.domain.assignment.service.AssignmentService;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/assignments")
public class AssignmentController {
    private final AssignmentService assignmentService;

    @PostMapping("/{accountId}")
    @ResponseStatus(CREATED)
    public AssignmentResponse createAssignment(@PathVariable UUID accountId, @RequestBody AssignmentRequest assignmentRequest) {
        return assignmentService.createAssignment(accountId, assignmentRequest);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public AssignmentResponses getAssignments() {
        return assignmentService.getAllAssignments();
    }

    @PutMapping(path = "/{assignmentId}")
    public AssignmentResponse updateAssignment(@PathVariable("assignmentId") UUID id, AssignmentRequest assignmentRequest) {
        return assignmentService.updateAssignmentById(id, assignmentRequest);
    }

    @DeleteMapping(path = "/{assignmentId}")
    @ResponseStatus(NO_CONTENT)
    public void deleteAssignment(@PathVariable("assignmentId") UUID id) {
        assignmentService.deleteAssignmentById(id);
    }
}