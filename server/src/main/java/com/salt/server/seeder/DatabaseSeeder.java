package com.salt.server.seeder;

import com.salt.server.assignment.model.Assignment;
import com.salt.server.assignment.repository.AssignmentRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.FileReader;

@Component
public class DatabaseSeeder implements ApplicationRunner {

    private final AssignmentRepository assignmentRepository;

    public DatabaseSeeder(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        testSeeder();
    }

    private void testSeeder() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(
                    ResourceUtils.getFile("server/src/main/resources/data/Scorecard - Test.csv")));

            String line;
            int index = 1;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] data = line.split(",");
                Assignment assignment = new Assignment();
                assignment.setName(data[0]);
//                assignment.setType(data[1]);
                assignmentRepository.save(assignment);

                index++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
