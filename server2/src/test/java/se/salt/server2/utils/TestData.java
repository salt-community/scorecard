package se.salt.server2.utils;

import lombok.experimental.UtilityClass;
import se.salt.server2.domain.account.controller.dto.AccountRequest;
import se.salt.server2.domain.account.controller.dto.AccountResponse;
import se.salt.server2.domain.account.models.AccountEntity;

import java.util.UUID;

@UtilityClass
public class TestData {
    public static final String BASE_URL_ACCOUNT = "/api/v2/account";
    public static final String MOCK_EMAIL = "carl@appliedtechnology.se";

    public static final String MOCK_UPDATED_EMAIL = "yolo@appliedtechnology.se";

    public static AccountRequest createNewAccountRequest() {
        return AccountRequest.builder().emailAddress(MOCK_EMAIL).build();
    }

    public static AccountResponse createNewAccountResponse() {
        return AccountResponse.builder()
                .id(UUID.randomUUID())
                .emailAddress(MOCK_EMAIL)
                .build();
    }

    public static AccountResponse createNewAccountResponse(UUID accountId) {
        return AccountResponse.builder().id(accountId).emailAddress(MOCK_EMAIL).build();
    }

    public static AccountEntity createNewAccountEntity() {
        return AccountEntity.builder()
                .id(UUID.randomUUID())
                .emailAddress(MOCK_EMAIL)
                .build();
    }

    public static AccountRequest updateAccountRequest() {
        return AccountRequest.builder().emailAddress(MOCK_UPDATED_EMAIL).build();
    }

    public static AccountResponse updateAccountResponse(UUID accountId) {
        return AccountResponse.builder().id(accountId).emailAddress(MOCK_UPDATED_EMAIL).build();
    }
}