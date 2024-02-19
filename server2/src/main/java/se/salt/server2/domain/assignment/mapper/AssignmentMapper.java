package se.salt.server2.domain.assignment.mapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import se.salt.server2.domain.assignment.controller.dto.AssignmentRequest;
import se.salt.server2.domain.assignment.controller.dto.AssignmentResponse;
import se.salt.server2.domain.assignment.controller.dto.AssignmentResponses;
import se.salt.server2.domain.assignment.models.AssignmentCategory;
import se.salt.server2.domain.assignment.models.AssignmentEntity;
import se.salt.server2.domain.developer.models.DeveloperEntity;
import se.salt.server2.domain.developer.repository.DeveloperRepository;

import java.util.List;

@Component
public class AssignmentMapper {





    public AssignmentEntity mapToAssignmentEntity(AssignmentRequest assignmentRequest, DeveloperEntity developer) {

        return AssignmentEntity.builder()
                .developer(developer)
                .title(assignmentRequest.title())
                .score(assignmentRequest.score())
                .description(assignmentRequest.description())
                .category(AssignmentCategory.fromString(assignmentRequest.category()))
                .build();
    }

    public AssignmentResponse mapToAssignmentResponse(AssignmentEntity assignmentEntity) {
        return AssignmentResponse.builder()
                .assignmentId(assignmentEntity.getId())
                .developerId(assignmentEntity.getDeveloper().getId())
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