package se.salt.server2.domain.assignment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.salt.server2.domain.assignment.controller.dto.AssignmentRequest;
import se.salt.server2.domain.assignment.controller.dto.AssignmentResponse;
import se.salt.server2.domain.assignment.controller.dto.AssignmentResponses;
import se.salt.server2.domain.assignment.mapper.AssignmentMapper;
import se.salt.server2.domain.assignment.models.AssignmentCategory;
import se.salt.server2.domain.assignment.models.AssignmentEntity;
import se.salt.server2.domain.assignment.repository.AssignmentRepository;
import se.salt.server2.exception.AssignmentDoesNotExistException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AssignmentService {
    private final AssignmentRepository assignmentRepository;
    private final AssignmentMapper assignmentMapper;

    public AssignmentResponse createAssignment(AssignmentRequest assignmentRequest) {
        return assignmentMapper.mapToAssignmentResponse(assignmentRepository.save(assignmentMapper.mapToAssignmentEntity(assignmentRequest)));
    }

    public AssignmentResponses getAllAssignments() {
        return assignmentMapper.mapToAssignmentResponses(assignmentRepository.findAll());
    }

    public AssignmentResponse getAssignmentById(UUID assignmentId) {
        return assignmentMapper.mapToAssignmentResponse(assignmentRepository
                .findById(assignmentId)
                .orElseThrow(() -> new AssignmentDoesNotExistException(assignmentId)));
    }

    public AssignmentResponses getAssignmentsByDeveloperId(UUID developerId) {
        return assignmentMapper.mapToAssignmentResponses(
                assignmentRepository.findByDeveloperId(developerId)
        );
    }

    public AssignmentResponse updateAssignmentById(UUID assignmentId, AssignmentRequest assignmentRequest) {
        AssignmentEntity assignment = assignmentRepository.findById(assignmentId).orElseThrow(() -> new AssignmentDoesNotExistException(assignmentId));
        assignment.setTitle(assignmentRequest.title());
        assignment.setScore(assignmentRequest.score());
        assignment.setDescription(assignmentRequest.description());
        assignment.setCategory(AssignmentCategory.valueOf(assignmentRequest.category()));

        return assignmentMapper.mapToAssignmentResponse(assignment);
    }

    public void deleteAssignmentById(UUID assignmentId) {
        assignmentRepository.deleteById(assignmentId);
    }
}