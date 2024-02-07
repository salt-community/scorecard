package com.salt.server.v1.assignment;

import com.salt.server.v1.assignment.model.Assignment;
import com.salt.server.v1.assignment.model.Coverage;
import com.salt.server.v1.assignment.model.Focus;
import com.salt.server.v1.assignment.repository.AssignmentRepository;
import com.salt.server.v1.assignment.repository.CoverageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final CoverageRepository coverageRepository;

    public AssignmentService(AssignmentRepository assignmentRepository, CoverageRepository coverageRepository) {
        this.assignmentRepository = assignmentRepository;
        this.coverageRepository = coverageRepository;
    }

    public List<Assignment> getAllTest() {
        return assignmentRepository.findAll();
    }

    public Assignment getTestById(UUID id) {
        return assignmentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("test not found"));
    }

    public Assignment getAssignmentByName(String name) {
        return assignmentRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("test not found"));
    }

    public void deleteAssignment(UUID id) {
        assignmentRepository.deleteById(id);
    }

    public Coverage getCoverageByAssignmentAndFocus(Assignment assignment, Focus focus) {
        return coverageRepository.findByAssignmentAndFocus(assignment, focus);
    }
}
