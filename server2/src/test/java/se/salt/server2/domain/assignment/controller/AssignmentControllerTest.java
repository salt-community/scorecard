package se.salt.server2.domain.assignment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import se.salt.server2.domain.assignment.controller.dto.AssignmentRequest;
import se.salt.server2.domain.assignment.controller.dto.AssignmentResponse;
import se.salt.server2.domain.assignment.controller.dto.AssignmentResponses;
import se.salt.server2.domain.assignment.service.AssignmentService;
import se.salt.server2.utils.TestData;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static se.salt.server2.utils.TestData.*;

@WebMvcTest(AssignmentController.class)
public class AssignmentControllerTest {

    @MockBean
    public AssignmentService assignmentService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @SneakyThrows
    void shouldReturnCreatedAssignmentAndReturn201Created() {

        AssignmentRequest assignmentRequest = TestData.createNewAssignmentRequest();

        when(assignmentService.createAssignment(assignmentRequest)).thenReturn(TestData.createnewAssignmentResponse());

        mockMvc.perform(post(BASE_URL_ASSIGNMENT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(assignmentRequest)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.assignmentId").exists())
                .andExpect(jsonPath("$.title").value("Weekend test 1"))
                .andExpect(jsonPath("$.score").value(10))
                .andExpect(jsonPath("$.description").value("Build api"));

    }

    @Test
    @SneakyThrows
    void shouldReturnListOfAssignmentResponseAnd200_Ok() {

        AssignmentResponses expectedResponses = new AssignmentResponses(List.of(TestData.createnewAssignmentResponse(), TestData.createnewAssignmentResponse()));

        when(assignmentService.getAllAssignments()).thenReturn(expectedResponses);

        mockMvc.perform(get(BASE_URL_ASSIGNMENT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.assignmentResponseList[0].title")
                        .value(expectedResponses.assignmentResponseList().get(0).title()));
    }

    @Test
    @SneakyThrows
    void shouldReturnSpecificAssignmentAnd200_Ok() {

        UUID assignmentId = UUID.randomUUID();
        AssignmentResponse originalResponse = TestData.createnewAssignmentResponse();
        AssignmentResponse expectedResponse = originalResponse.toBuilder().assignmentId(assignmentId).build();


        when(assignmentService.getAssignmentById(assignmentId)).thenReturn(originalResponse);

        mockMvc.perform(get(BASE_URL_ASSIGNMENT + "/{assignmentId}", assignmentId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value(expectedResponse.title()))
                .andExpect(jsonPath("$.title").value(expectedResponse.title()))
                .andExpect(jsonPath("$.score").value(expectedResponse.score()))
                .andExpect(jsonPath("$.description").value(expectedResponse.description()));
    }

}
