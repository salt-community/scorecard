package se.salt.server2.domain.background.mapper;

import org.springframework.stereotype.Component;
import se.salt.server2.domain.background.controller.dto.BackgroundRequest;
import se.salt.server2.domain.background.controller.dto.BackgroundResponse;
import se.salt.server2.domain.background.models.BackgroundEntity;
import se.salt.server2.domain.developer.models.Bootcamp;

@Component
public class BackgroundMapper {

    public BackgroundEntity mapToBackgroundEntity(BackgroundRequest backgroundRequest) {
        return BackgroundEntity.builder()
                .id(backgroundRequest.accountId())
                .firstName(backgroundRequest.firstName())
                .lastName(backgroundRequest.lastName())
                .githubUser(String.format("https://github.com/%s.png", backgroundRequest.githubUser()))
                .bootcamp(Bootcamp.valueOf(backgroundRequest.bootcampCourse()))
                .build();
    }

    public BackgroundResponse mapToBackgroundResponse(BackgroundEntity backgroundEntity) {
        return BackgroundResponse.builder()
                .developerId(backgroundEntity.getId())
                .githubUser(backgroundEntity.getGithubUser())
                .firstName(backgroundEntity.getFirstName())
                .lastName(backgroundEntity.getLastName())
                .bootcampCourse(String.valueOf(backgroundEntity.getBootcamp()))
                .build();
    }
}