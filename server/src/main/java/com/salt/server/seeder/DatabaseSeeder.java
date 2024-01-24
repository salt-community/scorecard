package com.salt.server.seeder;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.salt.server.Account.api.dto.AccountDto;
import com.salt.server.assignment.model.Assignment;
import com.salt.server.assignment.model.Coverage;
import com.salt.server.assignment.model.Focus;
import com.salt.server.assignment.repository.AssignmentRepository;
import com.salt.server.assignment.repository.CoverageRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

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
        assignmentSeeder();
        accountSeeder();
    }

    private void assignmentSeeder() {
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
                    saveAssignment.addCoverage(coverage);
                    coverageRepository.save(coverage);
                    count++;
                }
                index++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void accountSeeder() {
        String feng = "{\n" +
                "  \"email\": \"feng.yang@appliedtechnology.se\",\n" +
                "  \"name\": \"feng yang\",\n" +
                "  \"standoutIntro\": \"experienced in various of client projects\",\n" +
                "  \"bootcamp\": \"javascript\",\n" +
                "  \"githubUsername\": \"Finns841594\",\n" +
                "  \"linkedinUsername\": \"feng-yang-511361166\",\n" +
                "  \"codewarsUsername\" : \"Finns841594\",\n" +
                "  \"selectedProjects\": [\n" +
                "    \"https://github.com/Finns841594/moboga\",\n" +
                "    \"https://github.com/lups-tech/jobMatches\"\n" +
                "  ],\n" +
                "  \"backgroundInformation\": {\n" +
                "    \"nationalities\": [\"chinese\"],\n" +
                "    \"spokenLanguages\": {\n" +
                "        \"chinese\":\"natives\",\n" +
                "        \"english\":\"fluent\",\n" +
                "        \"swedish\": \"intermediate\"\n" +
                "    },\n" +
                "    \"academic\": {\n" +
                "        \"degree\" : \"master\",\n" +
                "        \"major\" : \"architecture\",\n" +
                "        \"startDate\" : \"12-08-2018\",\n" +
                "        \"endDate\" : \"12-08-2020\",\n" +
                "        \"yearStudied\" : 2,\n" +
                "        \"school\" : \"KTH Royal Institute of Technology\"\n" +
                "        },\n" +
                "    \"skills\": [\"javaScript\", \"typeScript\", \"react\", \"next.js\", \"node.js\", \"express\", \"mongodb\"]\n" +
                "  }\n" +
                "} ";

        String uri = "http://localhost:8080/api/accounts";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(feng,headers);
        AccountDto.AccountResponse accountResponse = restTemplate.postForObject(uri, entity, AccountDto.AccountResponse.class);
    }
}
