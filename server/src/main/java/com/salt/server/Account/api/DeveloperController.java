package com.salt.server.Account.api;

import com.salt.server.Account.api.dto.DeveloperDto;
import com.salt.server.Account.service.DeveloperService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/developers")
@CrossOrigin("http://localhost:3000")
public class DeveloperController {

    private final DeveloperService developerService;

    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @GetMapping
    public List<DeveloperDto.ShowcaseResponse> getAllDevelopers() {
        return developerService.getAllDeveloper();
    }

    @GetMapping("/{developerId}")
    public DeveloperDto.Response getDeveloperById(@PathVariable UUID developerId) {
        return developerService.getDeveloperByIdtoResponse(developerId);
    }

    @PostMapping
    public DeveloperDto.Response createDeveloper(@RequestBody DeveloperDto.Request request) {
        return developerService.createDeveloper(request);
    }

    @GetMapping("/admin")
    public List<DeveloperDto.AdminResponse> adminGetAllDevelopers() {
        return developerService.adminGetAllDevelopers();
    }

    @GetMapping("/admin/scoreboard")
    public List<DeveloperDto.ScoreboardResponse> adminGetAllSaltieScoreboard() {
        return developerService.adminGetAllSaltieScoreboard();
    }
}
