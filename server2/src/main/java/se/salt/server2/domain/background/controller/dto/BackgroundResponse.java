package se.salt.server2.domain.background.controller.dto;

import lombok.Builder;

import java.util.UUID;

@Builder(toBuilder = true)
public record BackgroundResponse(UUID developerId, String firstName, String lastName, String emailAddress,
                                 String bootcampCourse, String githubUser) {
}