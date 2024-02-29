package se.salt.server2.domain.scorecard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.salt.server2.domain.scorecard.controller.dto.ScorecardResponse;
import se.salt.server2.domain.scorecard.service.ScorecardService;

import java.util.UUID;

@RestController
@RequestMapping("api/v2/scorecard")
@RequiredArgsConstructor
public class ScorecardController {

    private final ScorecardService scorecardService;

    @GetMapping(
            path = "/{developerId}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ScorecardResponse getScorecardByDeveloperId(@PathVariable("developerId") UUID id) {
        return scorecardService.getScorecardByDeveloperId(id);
    }
}
