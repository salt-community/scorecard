package com.salt.server.assignment;

import com.salt.server.assignment.model.Assignment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/assignment")
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @GetMapping
    public List<Assignment> getAllTest() {
        return assignmentService.getAllTest();
    }

    @GetMapping("/{assignmentId}")
    public Assignment getAssignmentById(@PathVariable UUID assignmentId) {
        return assignmentService.getTestById(assignmentId);
    }

    @DeleteMapping("/{assignmentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteScore (@PathVariable UUID assignmentId) {
        assignmentService.deleteAssignment(assignmentId);
    }
}
