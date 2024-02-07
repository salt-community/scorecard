package se.salt.server2.domain.account.controller.dto;

import lombok.Builder;

import java.util.UUID;

@Builder(toBuilder = true)
public record AccountResponse(
        UUID id,

        String emailAddress
) {
}