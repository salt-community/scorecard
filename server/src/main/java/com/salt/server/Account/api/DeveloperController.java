package com.salt.server.Account.api;

import com.salt.server.Account.api.dto.DeveloperDto;
import com.salt.server.Account.service.DeveloperService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/developers")
@CrossOrigin(origins = {"http://localhost:3000", "https://scorecard-gold.vercel.app", "https://salt-scorecard.vercel.app"})
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

    @GetMapping("/admin/developer/{developerId}")
    public DeveloperDto.AdminDeveloper adminGetAllDevelopers(@PathVariable UUID developerId) {
        return developerService.adminGetAdminDeveloperById(developerId);
    }

    @GetMapping("/admin/scoreboard")
    public List<DeveloperDto.ScoreboardResponse> adminGetAllSaltieScoreboard() {
        return developerService.adminGetAllSaltieScoreboard();
    }

    @GetMapping("/admin/scoreboard/{developerId}")
    public DeveloperDto.DeveloperScoreboardResponse getDeveloperScoreboardById(@PathVariable UUID developerId) {
        return developerService.adminGetDeveloperScoreboard(developerId);
    }

    @PutMapping("/{developerId}")
    public DeveloperDto.Response updateDeveloper(@PathVariable UUID developerId, @RequestBody DeveloperDto.AdminDeveloper request) {
        return developerService.updateDeveloper(developerId ,request);
    }

    @DeleteMapping("/{developerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteScore (@PathVariable UUID developerId) {
        developerService.deleteDeveloperById(developerId);
    }
}
