package se.salt.server2.domain.background.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import se.salt.server2.domain.background.controller.dto.BackgroundRequest;
import se.salt.server2.domain.background.controller.dto.BackgroundResponse;
import se.salt.server2.domain.background.service.BackgroundService;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/backgrounds")
public class BackgroundController {

    private final BackgroundService backgroundService;

    @GetMapping(path = "/{backgroundId}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public BackgroundResponse getBackground(@PathVariable("backgroundId") UUID id) {
        return backgroundService.getBackgroundById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public BackgroundResponse createBackground(@RequestBody BackgroundRequest backgroundRequest) {
        return backgroundService.createBackground(backgroundRequest);
    }

    @PatchMapping(path = "/{backgroundId}")
    public BackgroundResponse updateBackground(@PathVariable("backgroundId") UUID id, @RequestBody BackgroundRequest backgroundRequest) {
        return backgroundService.updateBackgroundById(id, backgroundRequest);
    }

    @DeleteMapping(path = "/{backgroundId}")
    @ResponseStatus(NO_CONTENT)
    public void deleteBackground(@PathVariable("backgroundId") UUID id) {
        backgroundService.deleteBackgroundById(id);
    }
}
