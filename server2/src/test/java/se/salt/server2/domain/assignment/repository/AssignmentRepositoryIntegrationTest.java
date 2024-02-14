package se.salt.server2.domain.assignment.repository;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.salt.server2.domain.assignment.models.AssignmentCategory;
import se.salt.server2.domain.assignment.models.AssignmentEntity;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AssignmentRepositoryIntegrationTest {

    @Autowired
    AssignmentRepository assignmentRepository;

    @Test
    @SneakyThrows
    void shouldFindByIdAndReturnAssignment() {

        var assignment = new AssignmentEntity(
                UUID.randomUUID(), UUID.randomUUID(), "Weekend test 1", 86, "Well done!", AssignmentCategory.BACKEND);

        var assignmentRes = assignmentRepository.save(assignment);

        assertThat(assignmentRepository.findById(assignment.getId()).get().getId())
                .isEqualTo(assignmentRes.getId());
        assertThat(assignmentRepository.findById(assignment.getId()).get().getDescription())
                .isEqualTo(assignmentRes.getDescription());
    }
}