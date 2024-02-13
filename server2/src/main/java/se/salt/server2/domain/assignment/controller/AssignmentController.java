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

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/assignments")
@CrossOrigin("*")
public class AssignmentController {
    private final AssignmentService assignmentService;

    @PostMapping
    @ResponseStatus(CREATED)
    public AssignmentResponse createAssignment(@RequestBody AssignmentRequest assignmentRequest) {
        return assignmentService.createAssignment(assignmentRequest);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public AssignmentResponses getAssignments() {
        return assignmentService.getAllAssignments();
    }

    @GetMapping(
            path = "/{assignmentId}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public AssignmentResponse getAssignment(@PathVariable("assignmentId") UUID id) {
        return assignmentService.getAssignmentById(id);
    }

    @PutMapping(path = "/{assignmentId}")
    public AssignmentResponse updateAssignment(@PathVariable("assignmentId") UUID id, AssignmentRequest assignmentRequest) {
        return assignmentService.updateAssignmentById(id, assignmentRequest);
    }
}