package se.salt.server2.domain.developer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.salt.server2.domain.account.models.AccountEntity;
import se.salt.server2.domain.account.repository.AccountRepository;
import se.salt.server2.domain.developer.controller.dto.DeveloperRequest;
import se.salt.server2.domain.developer.controller.dto.DeveloperResponse;
import se.salt.server2.domain.developer.controller.dto.DeveloperResponses;
import se.salt.server2.domain.developer.mapper.DeveloperMapper;
import se.salt.server2.domain.developer.models.BootcampCourse;
import se.salt.server2.domain.developer.models.DeveloperEntity;
import se.salt.server2.domain.developer.repository.DeveloperRepository;
import se.salt.server2.exception.DeveloperDoesNotExistException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeveloperService {

    private final DeveloperRepository developerRepository;

    private final DeveloperMapper developerMapper;

    private final AccountRepository accountRepository;
    public DeveloperResponses getAllDevelopers() {
        return developerMapper.mapToDeveloperResponses(developerRepository.findAll());
    }

    public DeveloperResponse createDeveloper(DeveloperRequest developerRequest) {
        return developerMapper.mapToDeveloperResponse(developerRepository.save(developerMapper.mapToDeveloperEntity(developerRequest)));
    }

    public DeveloperResponse getDeveloperById(UUID developerId) {
        return developerMapper.mapToDeveloperResponse(developerRepository
                .findById(developerId)
                .orElseThrow(() -> new DeveloperDoesNotExistException(developerId)));
    }

    public DeveloperResponse updateDeveloperById(UUID developerId, DeveloperRequest developerRequest) {
        DeveloperEntity developer = developerRepository.findById(developerId).orElseThrow(() -> new DeveloperDoesNotExistException(developerId));
        developer.setFirstName(developerRequest.firstName());
        developer.setLastName(developerRequest.lastName());
        developer.setEmailAddress(developerRequest.emailAddress());
        developer.setBootcampCourse(BootcampCourse.valueOf(developerRequest.bootcampCourse()));

        return developerMapper.mapToDeveloperResponse(developer);
    }

    public void deleteDeveloperById(UUID developerId) {
        developerRepository.deleteById(developerId);
    }
}
