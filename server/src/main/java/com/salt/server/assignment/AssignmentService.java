package com.salt.server.assignment;

import com.salt.server.assignment.model.Assignment;
import com.salt.server.assignment.repository.AssignmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;

    public AssignmentService(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    public List<Assignment> getAllTest() {
        return assignmentRepository.findAll();
    }

    public Assignment getTestById(UUID id) {
        return assignmentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("test not found"));
    }

    public Assignment getTestByName(String name) {
        return assignmentRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("test not found"));
    }

    public void deleteAssignment(UUID id) {
        assignmentRepository.deleteById(id);
    }
}
