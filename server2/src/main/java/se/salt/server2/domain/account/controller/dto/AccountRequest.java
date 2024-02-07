package se.salt.server2.domain.account.controller.dto;

import lombok.Builder;

@Builder(toBuilder = true)
public record AccountRequest(
        String emailAddress
) {
}