package se.salt.server2.domain.developer.mapper;

import org.springframework.stereotype.Component;
import se.salt.server2.domain.developer.controller.dto.DeveloperRequest;
import se.salt.server2.domain.developer.controller.dto.DeveloperResponse;
import se.salt.server2.domain.developer.controller.dto.DeveloperResponses;
import se.salt.server2.domain.developer.models.BootcampCourse;
import se.salt.server2.domain.developer.models.DeveloperEntity;

import java.util.List;

@Component
public class DeveloperMapper {

    public DeveloperEntity mapToDeveloperEntity(DeveloperRequest developerRequest) {
        return DeveloperEntity.builder()
                .firstName(developerRequest.firstName())
                .LastName(developerRequest.lastName())
                .emailAddress(developerRequest.emailAddress())
                .bootcampCourse(BootcampCourse.fromString(developerRequest.bootcampCourse()))
                .build();
    }

    public DeveloperResponse mapToDeveloperResponse(DeveloperEntity developerEntity) {
        return DeveloperResponse.builder()
                .developerId(developerEntity.getId())
                .firstName(developerEntity.getFirstName())
                .lastName(developerEntity.getLastName())
                .emailAddress(developerEntity.getEmailAddress())
                .bootcampCourse(String.valueOf(developerEntity.getBootcampCourse()))
                .build();
    }

    public DeveloperResponses mapToDeveloperResponses(List<DeveloperEntity> developerEntityList) {
        List<DeveloperResponse> developerResponseList = developerEntityList.stream()
                .map(this::mapToDeveloperResponse)
                .toList();
        return DeveloperResponses.builder().developerResponseList(developerResponseList).build();
    }
}
