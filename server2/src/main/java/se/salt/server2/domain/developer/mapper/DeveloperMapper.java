package se.salt.server2.domain.developer.mapper;

import org.springframework.stereotype.Component;
import se.salt.server2.domain.developer.controller.dto.DeveloperRequest;
import se.salt.server2.domain.developer.controller.dto.DeveloperResponse;
import se.salt.server2.domain.developer.controller.dto.DeveloperResponses;
import se.salt.server2.domain.developer.models.Bootcamp;
import se.salt.server2.domain.developer.models.DeveloperEntity;

import java.util.List;

@Component
public class DeveloperMapper {

    public DeveloperEntity mapToDeveloperEntity(DeveloperRequest developerRequest) {
        return DeveloperEntity.builder()
                .accountId(developerRequest.accountId())
                .firstName(developerRequest.firstName())
                .LastName(developerRequest.lastName())
                .emailAddress(developerRequest.emailAddress())
                .bootcampCourse(Bootcamp.fromString(developerRequest.bootcampCourse()))
                .githubImage(String.format("https://github.com/%s.png", developerRequest.githubUsername()))
                .build();
    }

    public DeveloperResponse mapToDeveloperResponse(DeveloperEntity developerEntity) {
        developerEntity.calculateBackendAverageScore();
        developerEntity.calculateFrontendAverageScore();
        return DeveloperResponse.builder()
                .developerId(developerEntity.getId())
                .firstName(developerEntity.getFirstName())
                .lastName(developerEntity.getLastName())
                .emailAddress(developerEntity.getEmailAddress())
                .bootcampCourse(String.valueOf(developerEntity.getBootcampCourse()))
                .averageBackendScore(developerEntity.getAverageBackendScore())
                .averageFrontendScore(developerEntity.getAverageFrontendScore())
                .githubImage(developerEntity.getGithubImage())
                .build();
    }

    public DeveloperResponses mapToDeveloperResponses(List<DeveloperEntity> developerEntityList) {
        List<DeveloperResponse> developerResponseList = developerEntityList.stream()
                .map(this::mapToDeveloperResponse)
                .toList();
        return DeveloperResponses.builder().developerResponseList(developerResponseList).build();
    }
}
