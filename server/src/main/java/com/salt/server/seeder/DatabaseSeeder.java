package com.salt.server.seeder;

import com.salt.server.assignment.model.Assignment;
import com.salt.server.assignment.model.Coverage;
import com.salt.server.assignment.model.Focus;
import com.salt.server.assignment.repository.AssignmentRepository;
import com.salt.server.assignment.repository.CoverageRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.FileReader;

@Component
public class DatabaseSeeder implements ApplicationRunner {

    private final AssignmentRepository assignmentRepository;
    private final CoverageRepository coverageRepository;

    public DatabaseSeeder(AssignmentRepository assignmentRepository, CoverageRepository coverageRepository) {
        this.assignmentRepository = assignmentRepository;
        this.coverageRepository = coverageRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        testSeeder();
    }

    private void testSeeder() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(
                    ResourceUtils.getFile("server/src/main/resources/data/Scorecard - Assignment.csv")));

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
                assignment.setType(data[1]);
                Assignment saveAssignment = assignmentRepository.save(assignment);

                Focus[] focusTypes = Focus.values();
                int count = 2;

                for (Focus focus : focusTypes) {
                    Coverage coverage = new Coverage();
                    coverage.setAssignment(saveAssignment);
                    coverage.setFocus(focus.toString());
                    coverage.setPercentage(Integer.parseInt(data[count]));
                    coverageRepository.save(coverage);
                    count++;
                }
                index++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
