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
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


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
        String id = "8a9385a7-c944-43ba-b4aa-a9e9ff1be14e";
        Assignment assignment = new Assignment();
        assignment.setId(UUID.fromString(id));
        Assignment assignment2 = new Assignment();
        when(assignmentRepository.findById(assignment.getId())).thenReturn(java.util.Optional.of(assignment));
        Assignment returnedAssignment = assignmentService.getTestById(assignment.getId());
        assertThat(returnedAssignment).isEqualTo(assignment);
        assertThat(returnedAssignment.getId()).isEqualTo(UUID.fromString(id));
        assertThat(returnedAssignment).isNotEqualTo(assignment2);
    }

    @Test
    void shouldThrowNoSuchElementExceptionWhenAssignmentNotFound() {
        Assignment assignment = new Assignment();
        when(assignmentRepository.findById(assignment.getId())).thenReturn(java.util.Optional.empty());
        assertThrows(NoSuchElementException.class, () -> assignmentService.getTestById(assignment.getId()));
    }

    @Test
    void shouldReturnAssignmentByName(){
        Assignment assignment = new Assignment();
        assignment.setName("test");
        Assignment assignment2 = new Assignment();
        when(assignmentRepository.findByName(assignment.getName())).thenReturn(java.util.Optional.of(assignment));
        Assignment returnedAssignment = assignmentService.getTestByName(assignment.getName());
        assertThat(returnedAssignment).isEqualTo(assignment);
        assertThat(returnedAssignment.getName()).isEqualTo("test");
        assertThat(returnedAssignment).isNotEqualTo(assignment2);
    }

    @Test
    void shouldThrowNoSuchElementExceptionWhenAssignmentNotFoundByName() {
        Assignment assignment = new Assignment();
        assignment.setName("test");
        when(assignmentRepository.findByName(assignment.getName())).thenReturn(java.util.Optional.empty());
        assertThrows(NoSuchElementException.class, () -> assignmentService.getTestByName(assignment.getName()));
    }

    @Test
    void shouldDeleteAssignmentById() {
        String id = "8a9385a7-c944-43ba-b4aa-a9e9ff1be14e";
        assignmentService.deleteAssignment(UUID.fromString(id));
        verify(assignmentRepository, times(1)).deleteById(UUID.fromString(id));
    }

}