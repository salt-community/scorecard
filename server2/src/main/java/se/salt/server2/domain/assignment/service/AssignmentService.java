package se.salt.server2.domain.assignment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.salt.server2.domain.assignment.controller.dto.AssignmentRequest;
import se.salt.server2.domain.assignment.controller.dto.AssignmentResponse;
import se.salt.server2.domain.assignment.models.AssignmentCategory;
import se.salt.server2.domain.assignment.models.AssignmentEntity;
import se.salt.server2.domain.assignment.repository.AssignmentRepository;

@Service
@RequiredArgsConstructor
public class AssignmentService {
    private final AssignmentRepository assignmentRepository;

    public AssignmentResponse createAssignment(AssignmentRequest assignmentRequest){
        AssignmentEntity assignmentEntity = AssignmentEntity.builder()
                .accountId(assignmentRequest.accountId())
                .title(assignmentRequest.title())
                .score(assignmentRequest.score())
                .description(assignmentRequest.description())
                .category(AssignmentCategory.valueOf(assignmentRequest.category()))
                .build();

        AssignmentEntity savedAssignmentEntity = assignmentRepository.save(assignmentEntity);

        return AssignmentResponse.builder()
                .title(savedAssignmentEntity.getTitle())
                .score(savedAssignmentEntity.getScore())
                .description(savedAssignmentEntity.getDescription())
                .category(String.valueOf(savedAssignmentEntity.getCategory()))
                .build();
    }
}
