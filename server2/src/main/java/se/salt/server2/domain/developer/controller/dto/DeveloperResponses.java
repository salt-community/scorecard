package se.salt.server2.domain.developer.controller.dto;

import lombok.Builder;

import java.util.List;

@Builder(toBuilder = true)
public record DeveloperResponses(List<DeveloperResponse> developerResponseList) {
}
