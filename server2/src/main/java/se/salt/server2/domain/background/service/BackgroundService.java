package se.salt.server2.domain.background.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.salt.server2.domain.background.controller.dto.BackgroundRequest;
import se.salt.server2.domain.background.controller.dto.BackgroundResponse;
import se.salt.server2.domain.background.mapper.BackgroundMapper;
import se.salt.server2.domain.background.models.BackgroundEntity;
import se.salt.server2.domain.background.repository.BackgroundRepository;
import se.salt.server2.domain.developer.models.Bootcamp;
import se.salt.server2.exception.BackgroundDoesNotExistException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BackgroundService {

    private final BackgroundRepository backgroundRepository;

    private final BackgroundMapper backgroundMapper;

    public BackgroundResponse getBackgroundById(UUID backgroundId) {
        return backgroundMapper.mapToBackgroundResponse(
                backgroundRepository.findById(backgroundId)
                        .orElseThrow(() -> new BackgroundDoesNotExistException(backgroundId)));
    }

    public BackgroundResponse createBackground(BackgroundRequest backgroundRequest) {
        return backgroundMapper.mapToBackgroundResponse(backgroundRepository.save(backgroundMapper.mapToBackgroundEntity(backgroundRequest)));
    }

    public BackgroundResponse updateBackgroundById(UUID backgroundId, BackgroundRequest backgroundRequest) {
        BackgroundEntity background = backgroundRepository.findById(backgroundId).orElseThrow(() -> new BackgroundDoesNotExistException(backgroundId));

        if(backgroundRequest.firstName() != null ) {
            background.setFirstName(backgroundRequest.firstName());
        }

        if(backgroundRequest.lastName() != null ) {
            background.setLastName(backgroundRequest.lastName());
        }

        if(backgroundRequest.bootcampCourse() != null ) {
            background.setBootcamp(Bootcamp.valueOf(backgroundRequest.bootcampCourse()));
        }

        if(backgroundRequest.githubUser() != null ) {
            background.setGithubUser(backgroundRequest.githubUser());
        }
        backgroundRepository.save(background);

        return backgroundMapper.mapToBackgroundResponse(background);
    }

    public void deleteBackgroundById(UUID backgroundId) {
        backgroundRepository.deleteById(backgroundId);
    }
}