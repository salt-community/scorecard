package se.salt.server2.domain.assignment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.salt.server2.domain.account.models.AccountEntity;
import se.salt.server2.domain.account.repository.AccountRepository;
import se.salt.server2.domain.assignment.controller.dto.AssignmentRequest;
import se.salt.server2.domain.assignment.controller.dto.AssignmentResponse;
import se.salt.server2.domain.assignment.controller.dto.AssignmentResponses;
import se.salt.server2.domain.assignment.mapper.AssignmentMapper;
import se.salt.server2.domain.assignment.models.AssignmentCategory;
import se.salt.server2.domain.assignment.models.AssignmentEntity;
import se.salt.server2.domain.assignment.repository.AssignmentRepository;
import se.salt.server2.domain.associations.account_assignment.AccountAssignmentMapping;
import se.salt.server2.domain.associations.account_assignment.AccountAssignmentRepository;
import se.salt.server2.exception.AssignmentDoesNotExistException;
import se.salt.server2.exception.DeveloperDoesNotExistException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssignmentService {
    private final AssignmentRepository assignmentRepository;
    private final AssignmentMapper assignmentMapper;

    private final AccountRepository accountRepository;
    private final AccountAssignmentRepository accountAssignmentRepository;

    public AssignmentEntity createAssignment(AssignmentRequest assignmentRequest) {
        return assignmentRepository.save(assignmentMapper.mapToAssignmentEntity(assignmentRequest));
    }

    public AssignmentResponse addAssignmentToDeveloper(AssignmentRequest assignmentRequest) {
        AccountEntity account = accountRepository.findById(assignmentRequest.developerId()).orElseThrow(() -> new DeveloperDoesNotExistException(assignmentRequest.developerId()));
        AssignmentEntity assignment = assignmentRepository.save(assignmentMapper.mapToAssignmentEntity(assignmentRequest));

        AccountAssignmentMapping mapping = AccountAssignmentMapping.builder()
                .account(account)
                .assignment(assignment)
                .score(assignmentRequest.score())
                .build();

        return mapToResponse(mapping);
    }

    public AssignmentResponses getAllAssignments() {
        return assignmentMapper.mapToAssignmentResponses(assignmentRepository.findAll());
    }

    public List<AssignmentResponse> getAssignmentsByAccountId(UUID accountId) {
        return accountAssignmentRepository.findAllByAccountId(accountId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public AssignmentResponse updateAssignmentById(UUID assignmentId, AssignmentRequest assignmentRequest) {

       accountRepository.findById(assignmentRequest.developerId()).orElseThrow(() -> new DeveloperDoesNotExistException(assignmentRequest.developerId()));

        var mapping =
                accountAssignmentRepository.findById(assignmentId).orElseThrow(() -> new AssignmentDoesNotExistException(assignmentId));

        mapping.getAssignment().setTitle(assignmentRequest.title());
        mapping.getAssignment().setDescription(assignmentRequest.description());
        mapping.getAssignment().setCategory(AssignmentCategory.valueOf(assignmentRequest.category()));

        accountAssignmentRepository.save(mapping);

        return mapToResponse(mapping);
    }

    public void deleteAssignmentById(UUID assignmentId) {
        accountAssignmentRepository.deleteById(assignmentId);
    }

    private AssignmentResponse mapToResponse(AccountAssignmentMapping mapping) {
        return AssignmentResponse.builder()
                .assignmentId(mapping.getAssignment().getId())
                .title(mapping.getAssignment().getTitle())
                .score(mapping.getScore())
                .description(mapping.getAssignment().getDescription())
                .category(String.valueOf(mapping.getAssignment().getCategory()))
                .build();
    }
}