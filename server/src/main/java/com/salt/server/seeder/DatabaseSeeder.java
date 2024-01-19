package com.salt.server.seeder;

import com.salt.server.test.Test;
import com.salt.server.test.TestRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.FileReader;

@Component
public class DatabaseSeeder implements ApplicationRunner {

    private final TestRepository testRepository;

    public DatabaseSeeder(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        testSeeder();
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
                Test test = new Test();
                test.setName(data[0]);
                test.setType(data[1]);
                testRepository.save(test);

                index++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
