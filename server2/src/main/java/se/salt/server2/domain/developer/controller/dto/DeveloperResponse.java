package se.salt.server2.domain.developer.controller.dto;

import lombok.Builder;

import java.util.UUID;

@Builder(toBuilder = true)
public record DeveloperResponse(UUID developerId, String firstName, String lastName, String emailAddress,
                                String bootcampCourse, int averageBackendScore, int averageFrontendScore,
                                String githubUser) {
}