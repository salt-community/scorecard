package se.salt.server2.domain.assignment.mapper;

import org.springframework.stereotype.Component;
import se.salt.server2.domain.assignment.controller.dto.AssignmentRequest;
import se.salt.server2.domain.assignment.controller.dto.AssignmentResponse;
import se.salt.server2.domain.assignment.controller.dto.AssignmentResponses;
import se.salt.server2.domain.assignment.models.AssignmentCategory;
import se.salt.server2.domain.assignment.models.AssignmentEntity;

import java.util.List;

@Component
public class AssignmentMapper {

    public AssignmentEntity mapToAssignmentEntity(AssignmentRequest assignmentRequest) {
        return AssignmentEntity.builder()
                .accountId(assignmentRequest.accountId())
                .title(assignmentRequest.title())
                .score(assignmentRequest.score())
                .description(assignmentRequest.description())
                .category(AssignmentCategory.fromString(assignmentRequest.category()))
                .build();
    }

    public AssignmentResponse mapToAssignmentResponse(AssignmentEntity assignmentEntity) {
        return AssignmentResponse.builder()
                .assignmentId(assignmentEntity.getId())
                .accountID(assignmentEntity.getAccountId())
                .title(assignmentEntity.getTitle())
                .score(assignmentEntity.getScore())
                .description(assignmentEntity.getDescription())
                .category(String.valueOf(assignmentEntity.getCategory()))
                .build();
    }

    public AssignmentResponses mapToAssignmentResponses(List<AssignmentEntity> assignmentEntityList) {
        List<AssignmentResponse> assignmentResponseList = assignmentEntityList.stream()
                .map(this::mapToAssignmentResponse)
                .toList();

        return AssignmentResponses.builder()
                .assignmentResponseList(assignmentResponseList)
                .build();

    }
}