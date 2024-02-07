package se.salt.server2.domain.account.controller.dto;

import lombok.Builder;

import java.util.List;

@Builder(toBuilder = true)
public record AccountResponses(List<AccountResponse> accountResponseList) {}
