package se.salt.server2.domain.developer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.salt.server2.domain.account.repository.AccountRepository;
import se.salt.server2.domain.associations.account_role.AccountRoleMapping;
import se.salt.server2.domain.associations.account_role.AccountRoleRepository;
import se.salt.server2.domain.background.models.BackgroundEntity;
import se.salt.server2.domain.developer.controller.dto.DeveloperRequest;
import se.salt.server2.domain.developer.controller.dto.DeveloperResponse;
import se.salt.server2.domain.developer.controller.dto.DeveloperResponses;
import se.salt.server2.domain.developer.models.Bootcamp;
import se.salt.server2.domain.role.repository.RoleRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeveloperService {
    private final AccountRoleRepository accountRoleRepository;
    private final RoleRepository roleRepository;
    private final AccountRepository accountRepository;

    public DeveloperResponses getAllDevelopers() {
        var developerRole = roleRepository.findByName("developer");
       return  DeveloperResponses.builder().developerResponseList(
               accountRoleRepository.findAllByRoleId(developerRole.getId()).stream()
                       .map(this::mapToResponse).toList()
       ).build();
    }

    public DeveloperResponse createDeveloper(DeveloperRequest developerRequest) {
        var account = accountRepository.findById(developerRequest.accountId()).orElseThrow();

        var background = BackgroundEntity.builder()
                .firstName(developerRequest.firstName())
                .lastName(developerRequest.lastName())
                .githubUser(developerRequest.githubUsername())
                .bootcamp(Bootcamp.fromString(developerRequest.bootcamp()))
                .account(account)
                .build();

        account.setBackground(background);

        var developerRole = roleRepository.findByName("developer");

        var mapping = AccountRoleMapping.builder().account(account).role(developerRole).build();
        accountRoleRepository.save(mapping);
        return mapToResponse(mapping);
    }

    public DeveloperResponse getDeveloperById(UUID developerId) {
        var developerRole = roleRepository.findByName("developer");

        return mapToResponse(accountRoleRepository.findByRoleIdAndAccountId(developerRole.getId(), developerId));
    }

    private DeveloperResponse mapToResponse(AccountRoleMapping mapping) {
        return DeveloperResponse.builder()
                .developerId(mapping.getAccount().getId())
                .emailAddress(mapping.getAccount().getEmailAddress())
                .firstName(mapping.getAccount().getBackground().getFirstName())
                .lastName(mapping.getAccount().getBackground().getLastName())
                .githubUser(mapping.getAccount().getBackground().getGithubUser())
                .bootcampCourse(mapping.getAccount().getBackground().getBootcamp().getValue())

                .build();
    }

    public void deleteDeveloperById(UUID developerId) {
        accountRoleRepository.deleteById(developerId);
    }
}
