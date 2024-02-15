package se.salt.server2.domain.developer.controller.dto;

import lombok.Builder;

@Builder(toBuilder = true)
public record DeveloperRequest(String firstName, String lastName, String emailAddress, String bootcampCourse) {
}
