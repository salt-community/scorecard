package com.salt.server.seeder;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.salt.server.Account.api.dto.AccountDto;
import com.salt.server.Account.api.dto.ScoreDto;
import com.salt.server.Account.model.Account;
import com.salt.server.Account.repository.AccountRepository;
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
import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseSeeder implements ApplicationRunner {

    private final AssignmentRepository assignmentRepository;
    private final CoverageRepository coverageRepository;
    private final AccountRepository accountRepository;

    public DatabaseSeeder(AssignmentRepository assignmentRepository, CoverageRepository coverageRepository, AccountRepository accountRepository) {
        this.assignmentRepository = assignmentRepository;
        this.coverageRepository = coverageRepository;
        this.accountRepository = accountRepository;
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
        List<String> developers = Arrays.asList(feng, kevin);

        String uri = "http://localhost:8080/api/accounts";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        for (String developer : developers) {
            HttpEntity<String> entity = new HttpEntity<>(developer, headers);
            restTemplate.postForObject(uri, entity, AccountDto.AccountResponse.class);
        }

        List<Account> accounts = accountRepository.findAll();

        for (Account account : accounts) {
            String scoreUri = String.format("http://localhost:8080/api/scores/%s/add-scores", account.getId());
            HttpEntity<String> entity = new HttpEntity<>(score, headers);
            restTemplate.postForObject(scoreUri, entity, ScoreDto.ScoreResponse[].class);
        }

    }

    String score = "[\n" +
            "    {\n" +
            "        \"name\": \"hackday 2\",\n" +
            "        \"score\": 82,\n" +
            "        \"description\": \"good application\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"on site demo 1\",\n" +
            "        \"score\": 70,\n" +
            "        \"description\": \"good application\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"tech interview 1\",\n" +
            "        \"score\": 92,\n" +
            "        \"description\": \"good application\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"repository documentation\",\n" +
            "        \"score\": 50,\n" +
            "        \"description\": \"good application\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"tech article\",\n" +
            "        \"score\": 65,\n" +
            "        \"description\": \"good application\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"microsteps\",\n" +
            "        \"score\": 20,\n" +
            "        \"description\": \"good application\"\n" +
            "    },{\n" +
            "        \"name\": \"figma\",\n" +
            "        \"score\": 90,\n" +
            "        \"description\": \"good application\"\n" +
            "    },{\n" +
            "        \"name\": \"weekend assignment 1\",\n" +
            "        \"score\": 82,\n" +
            "        \"description\": \"good application\"\n" +
            "    },{\n" +
            "        \"name\": \"on site test 1\",\n" +
            "        \"score\": 87,\n" +
            "        \"description\": \"good application\"\n" +
            "    },{\n" +
            "        \"name\": \"hackday 1\",\n" +
            "        \"score\": 85,\n" +
            "        \"description\": \"good application\"\n" +
            "    },{\n" +
            "        \"name\": \"project 1\",\n" +
            "        \"score\": 95,\n" +
            "        \"description\": \"good application\"\n" +
            "    }\n" +
            "]";

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

    String kevin = "{\n" +
            "  \"email\": \"kevin.gida@appliedtechnology.se\",\n" +
            "  \"name\": \"kevin gida\",\n" +
            "  \"standoutIntro\": \"experienced in various of client projects\",\n" +
            "  \"bootcamp\": \"java\",\n" +
            "  \"githubUsername\": \"kevingida\",\n" +
            "  \"linkedinUsername\": \"kevingida\",\n" +
            "  \"codewarsUsername\" : \"kevingida\",\n" +
            "  \"selectedProjects\": [\n" +
            "    \"https://github.com/kevingida/classic-car\",\n" +
            "    \"https://github.com/TypoTitans/figure-forge-shop\"\n" +
            "  ],\n" +
            "  \"backgroundInformation\": {\n" +
            "    \"nationalities\": [\"indonesian\"],\n" +
            "    \"spokenLanguages\": {\n" +
            "        \"indonesian\":\"natives\",\n" +
            "        \"english\":\"fluent\",\n" +
            "        \"swedish\": \"beginner\"\n" +
            "    },\n" +
            "    \"academic\": {\n" +
            "        \"degree\" : \"master\",\n" +
            "        \"major\" : \"engineering\",\n" +
            "        \"startDate\" : \"14-08-2017\",\n" +
            "        \"endDate\" : \"01-08-2018\",\n" +
            "        \"yearStudied\" : 1,\n" +
            "        \"school\" : \"Cranfield University\"\n" +
            "        },\n" +
            "    \"skills\": [\"Java\",\"javaScript\", \"typeScript\", \"react\", \"next.js\", \"node.js\", \"express\", \"mongodb\",\"spring\",\"postgresql\"]\n" +
            "  }\n" +
            "} ";
}
