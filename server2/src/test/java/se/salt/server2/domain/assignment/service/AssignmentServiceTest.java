package se.salt.server2.domain.assignment.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.salt.server2.domain.assignment.controller.dto.AssignmentRequest;
import se.salt.server2.domain.assignment.controller.dto.AssignmentResponse;
import se.salt.server2.domain.assignment.controller.dto.AssignmentResponses;
import se.salt.server2.domain.assignment.mapper.AssignmentMapper;
import se.salt.server2.domain.assignment.models.AssignmentEntity;
import se.salt.server2.domain.assignment.repository.AssignmentRepository;
import se.salt.server2.domain.developer.models.DeveloperEntity;
import se.salt.server2.domain.developer.repository.DeveloperRepository;
import se.salt.server2.utils.TestData;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static se.salt.server2.utils.TestData.createNewDeveloperEntity;

@ExtendWith(MockitoExtension.class)
public class AssignmentServiceTest {
    @Mock
    private AssignmentRepository assignmentRepository;

    @Mock
    private AssignmentMapper assignmentMapper;

    @Mock
    private DeveloperRepository developerRepository;
    @InjectMocks
    private AssignmentService assignmentService;


    @Test
    void shouldCreateAssignment() {

        AssignmentEntity assignmentEntity = TestData.createNewAssignmentEntity();
        AssignmentRequest assignmentRequest = TestData.createNewAssignmentRequest(UUID.randomUUID());
        AssignmentResponse expectedResponse = TestData.createnewAssignmentResponse();
        DeveloperEntity developer = createNewDeveloperEntity();
        when(developerRepository.findById(assignmentRequest.developerId())).thenReturn(Optional.of(developer));
        when(assignmentMapper.mapToAssignmentEntity(assignmentRequest, developer)).thenReturn(assignmentEntity);
        when(assignmentRepository.save(assignmentEntity)).thenReturn(assignmentEntity);
        when(assignmentMapper.mapToAssignmentResponse(assignmentEntity)).thenReturn(expectedResponse);

        AssignmentResponse actualResponse = assignmentService.createAssignment(assignmentRequest);

        assertEquals(expectedResponse, actualResponse);
        verify(assignmentMapper).mapToAssignmentEntity(assignmentRequest, developer);
        verify(assignmentRepository).save(assignmentEntity);
        verify(assignmentMapper).mapToAssignmentResponse(assignmentEntity);
    }

    @Test
    void shouldGetAllAssignments() {

        List<AssignmentEntity> assignmentEntities = List.of(TestData.createNewAssignmentEntity(), TestData.createNewAssignmentEntity());
        AssignmentResponses expectedResponses = new AssignmentResponses(List.of(TestData.createnewAssignmentResponse(), TestData.createnewAssignmentResponse()));

        when(assignmentRepository.findAll()).thenReturn(assignmentEntities);
        when(assignmentMapper.mapToAssignmentResponses(assignmentEntities)).thenReturn(expectedResponses);

        AssignmentResponses actualResponses = assignmentService.getAllAssignments();

        assertEquals(expectedResponses, actualResponses);
        verify(assignmentRepository).findAll();
        verify(assignmentMapper).mapToAssignmentResponses(assignmentEntities);

    }

    @Test
    void shouldFindAssignmentbyId() {

        UUID assignmentId = UUID.randomUUID();
        AssignmentEntity assignmentEntity = TestData.createNewAssignmentEntity();
        assignmentEntity.setId(assignmentId);
        AssignmentResponse expectedResponse = TestData.createnewAssignmentResponse();

        when(assignmentRepository.findById(assignmentId)).thenReturn(Optional.of(assignmentEntity));
        when(assignmentMapper.mapToAssignmentResponse(assignmentEntity)).thenReturn(expectedResponse);

        AssignmentResponse actualResponse = assignmentService.getAssignmentById(assignmentId);

        assertEquals(expectedResponse, actualResponse);
        verify(assignmentRepository).findById(assignmentId);
        verify(assignmentMapper).mapToAssignmentResponse(assignmentEntity);


    }


}
