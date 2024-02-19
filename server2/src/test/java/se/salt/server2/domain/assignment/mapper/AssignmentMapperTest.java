package se.salt.server2.domain.assignment.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.salt.server2.domain.assignment.controller.dto.AssignmentRequest;
import se.salt.server2.domain.assignment.controller.dto.AssignmentResponse;
import se.salt.server2.domain.assignment.controller.dto.AssignmentResponses;
import se.salt.server2.domain.assignment.models.AssignmentCategory;
import se.salt.server2.domain.assignment.models.AssignmentEntity;
import se.salt.server2.utils.TestData;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static se.salt.server2.utils.TestData.createNewDeveloperEntity;

import java.util.List;

public class AssignmentMapperTest {

    private AssignmentMapper assignmentMapper;

    @BeforeEach
    public void setUp() {
        assignmentMapper = new AssignmentMapper();
    }

    @Test
    public void testMapToAssignmentEntity() {

        AssignmentRequest assignmentRequest = TestData.createNewAssignmentRequest();
        AssignmentEntity assignmentEntity = assignmentMapper.mapToAssignmentEntity(assignmentRequest, createNewDeveloperEntity());

        assertEquals(assignmentRequest.developerId(), assignmentEntity.getDeveloper().getId());
        assertEquals("Weekend test 1", assignmentEntity.getTitle());
        assertEquals(10, assignmentEntity.getScore());
        assertEquals("Build api", assignmentEntity.getDescription());
        assertEquals(AssignmentCategory.BACKEND, assignmentEntity.getCategory());
    }

    @Test
    public void testMapToAssignmentResponse() {

        AssignmentEntity assignmentEntity = TestData.createNewAssignmentEntity();
        AssignmentResponse assignmentResponse = assignmentMapper.mapToAssignmentResponse(assignmentEntity);

        assertEquals(assignmentEntity.getDeveloper().getId(), assignmentResponse.developerId());
        assertEquals(assignmentEntity.getId(), assignmentResponse.assignmentId());
        assertEquals(assignmentEntity.getTitle(), assignmentResponse.title());
        assertEquals(assignmentEntity.getScore(), assignmentResponse.score());
        assertEquals(assignmentEntity.getDescription(), assignmentResponse.description());
        assertEquals(assignmentEntity.getCategory().toString(), assignmentResponse.category());

    }

    @Test
    public void testMapToAssignmentResponses() {

        List<AssignmentEntity> assignmentEntityList = List.of(TestData.createNewAssignmentEntity(), TestData.createNewAssignmentEntity());
        AssignmentResponses assignmentResponses = assignmentMapper.mapToAssignmentResponses(assignmentEntityList);
        AssignmentResponse assignmentResponse = assignmentResponses.assignmentResponseList().get(0);
        AssignmentEntity assignmentEntity = assignmentEntityList.get(0);

        assertThat(assignmentResponses.assignmentResponseList()).hasSize(2);
        assertThat(assignmentResponses.assignmentResponseList().get(0)).isInstanceOf(AssignmentResponse.class);
        assertEquals(assignmentResponse.assignmentId(), assignmentEntity.getId());
        assertEquals(assignmentResponse.developerId(), assignmentEntity.getDeveloper().getId());
        assertEquals(assignmentResponse.title(), assignmentEntity.getTitle());
        assertEquals(assignmentResponse.score(), assignmentEntity.getScore());
        assertEquals(assignmentResponse.description(), assignmentEntity.getDescription());
        assertEquals(assignmentResponse.category(), assignmentEntity.getCategory().toString());

    }


}
