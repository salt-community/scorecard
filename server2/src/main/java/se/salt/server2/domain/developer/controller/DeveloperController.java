package se.salt.server2.domain.developer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import se.salt.server2.domain.developer.controller.dto.DeveloperRequest;
import se.salt.server2.domain.developer.controller.dto.DeveloperResponse;
import se.salt.server2.domain.developer.controller.dto.DeveloperResponses;
import se.salt.server2.domain.developer.service.DeveloperService;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/developers")
@CrossOrigin("*")
public class DeveloperController {

    private final DeveloperService developerService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public DeveloperResponses getDevelopers() {
        return developerService.getAllDevelopers();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public DeveloperResponse createDeveloper(@RequestBody DeveloperRequest developerRequest) {
        return developerService.createDeveloper(developerRequest);
    }

    @GetMapping(path = "/{developerId}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public DeveloperResponse getDeveloper(@PathVariable("developerId") UUID id) {
        return developerService.getDeveloperById(id);
    }

    @PutMapping(path = "/{developerId}")
    public DeveloperResponse updateDeveloper(@PathVariable("developerId") UUID id, DeveloperRequest developerRequest) {
        return developerService.updateDeveloperById(id, developerRequest);
    }

    @DeleteMapping(path = "/{developerId}")
    @ResponseStatus(NO_CONTENT)
    public void deleteDeveloper(@PathVariable("developerId") UUID id) {
        developerService.deleteDeveloperById(id);
    }
}
