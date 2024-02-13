package se.salt.server2.domain.assignment.repository;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.salt.server2.domain.assignment.models.AssignmentCategory;
import se.salt.server2.domain.assignment.models.AssignmentEntity;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class AssignmentRepositoryIntegrationTest {
    @Autowired
    AssignmentRepository assignmentRepository;
    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    @Test
    public void whenFindById_thenReturnAssignment() {
        AssignmentEntity newAssignment = new AssignmentEntity(
                UUID.randomUUID(),
                UUID.randomUUID(),
                "Weekend test 1",
                86,
                "Well done!",
                AssignmentCategory.BACKEND
        );

        AssignmentEntity foundAssignment = assignmentRepository.save(newAssignment);

        assertThat(entityManager.find(AssignmentEntity.class, foundAssignment.getId())).isEqualTo(newAssignment);


    }


}