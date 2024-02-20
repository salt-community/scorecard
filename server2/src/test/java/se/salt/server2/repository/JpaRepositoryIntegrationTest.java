package se.salt.server2.repository;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.salt.server2.domain.assignment.repository.AssignmentRepository;
import se.salt.server2.domain.developer.repository.DeveloperRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static se.salt.server2.utils.TestData.createNewAssignmentEntity;
import static se.salt.server2.utils.TestData.createNewDeveloperEntity;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JpaRepositoryIntegrationTest {

    @Autowired
    AssignmentRepository assignmentRepository;

    @Autowired
    DeveloperRepository developerRepository;
    @Test
    @SneakyThrows
    void shouldFindByIdAndReturnAssignment() {
        var developer = createNewDeveloperEntity();
        developerRepository.save(developer);
        var assignment = createNewAssignmentEntity();
        assignment.setDeveloper(developer);
        var assignmentRes = assignmentRepository.save(assignment);

        assertThat(assignmentRepository.findById(assignment.getId()).get().getId())
                .isEqualTo(assignmentRes.getId());
        assertThat(assignmentRepository.findById(assignment.getId()).get().getDescription())
                .isEqualTo(assignmentRes.getDescription());
    }
}