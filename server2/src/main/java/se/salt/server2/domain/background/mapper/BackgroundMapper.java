package se.salt.server2.domain.background.mapper;

import org.springframework.stereotype.Component;
import se.salt.server2.domain.background.controller.dto.BackgroundRequest;
import se.salt.server2.domain.background.controller.dto.BackgroundResponse;
import se.salt.server2.domain.background.models.BackgroundEntity;
import se.salt.server2.domain.background.models.BootcampCourse;

@Component
public class BackgroundMapper {

    public BackgroundEntity mapToBackgroundEntity(BackgroundRequest backgroundRequest) {
        return BackgroundEntity.builder()
                .id(backgroundRequest.accountId())
                .emailAddress(backgroundRequest.emailAddress())
                .firstName(backgroundRequest.firstName())
                .LastName(backgroundRequest.lastName())
                .githubUser(String.format("https://github.com/%s.png", backgroundRequest.githubUser()))
                .bootcampCourse(BootcampCourse.valueOf(backgroundRequest.bootcampCourse()))
                .build();
    }

    public BackgroundResponse mapToBackgroundResponse(BackgroundEntity backgroundEntity) {
        return BackgroundResponse.builder()
                .developerId(backgroundEntity.getId())
                .emailAddress(backgroundEntity.getEmailAddress())
                .githubUser(backgroundEntity.getGithubUser())
                .firstName(backgroundEntity.getFirstName())
                .lastName(backgroundEntity.getLastName())
                .bootcampCourse(String.valueOf(backgroundEntity.getBootcampCourse()))
                .build();
    }
}