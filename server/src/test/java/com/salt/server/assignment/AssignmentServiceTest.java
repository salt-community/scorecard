package com.salt.server.assignment;

import com.salt.server.assignment.model.Assignment;
import com.salt.server.assignment.repository.AssignmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AssignmentServiceTest {

    @Mock
    private AssignmentRepository assignmentRepository;

    @InjectMocks
    private AssignmentService assignmentService;

    @Test
    void shouldReturnListOfAllAssignments() {
        Assignment assignment1 = new Assignment();
        Assignment assignment2 = new Assignment();
        when(assignmentRepository.findAll()).thenReturn(List.of(assignment1, assignment2));
        assertThat(assignmentService.getAllTest()).containsExactly(assignment1, assignment2);
    }

    @Test
    void shouldReturnAssignmentById() {
        Assignment assignment = new Assignment();
        Assignment assignment2 = new Assignment();
        when(assignmentRepository.findById(assignment.getId())).thenReturn(java.util.Optional.of(assignment));
        Assignment returnedAssignment = assignmentService.getTestById(assignment.getId());
        assertThat(returnedAssignment).isEqualTo(assignment);
        assertThat(returnedAssignment).isNotEqualTo(assignment2);
    }

    @Test
    void shouldThrowNoSuchElementExceptionWhenAssignmentNotFound() {
        Assignment assignment = new Assignment();
        when(assignmentRepository.findById(assignment.getId())).thenReturn(java.util.Optional.empty());
        assertThrows(NoSuchElementException.class, () -> assignmentService.getTestById(assignment.getId()));
    }

}