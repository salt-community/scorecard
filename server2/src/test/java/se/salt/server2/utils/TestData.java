package se.salt.server2.utils;

import lombok.experimental.UtilityClass;
import se.salt.server2.domain.account.controller.dto.AccountRequest;
import se.salt.server2.domain.account.controller.dto.AccountResponse;
import se.salt.server2.domain.account.models.AccountEntity;
import se.salt.server2.domain.assignment.controller.dto.AssignmentRequest;
import se.salt.server2.domain.assignment.controller.dto.AssignmentResponse;
import se.salt.server2.domain.assignment.models.AssignmentCategory;
import se.salt.server2.domain.assignment.models.AssignmentEntity;
import se.salt.server2.domain.developer.models.DeveloperEntity;

import java.util.UUID;

@UtilityClass
public class TestData {
    public static final String BASE_URL_ACCOUNT = "/api/v2/accounts";

    public static final String BASE_URL_ASSIGNMENT = "/api/v2/assignments";
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


    public static AssignmentRequest createNewAssignmentRequest(UUID developerId) {
        return AssignmentRequest.builder()
                .developerId(developerId)
                .category("Backend")
                .description("Build api")
                .score(10)
                .title("Weekend test 1")
                .build();
    }

    public static AssignmentEntity createNewAssignmentEntity() {
        return AssignmentEntity.builder()
                .id(UUID.randomUUID())
                .developer(createNewDeveloperEntity())
                .title("Title")
                .score(8)
                .description("Description")
                .category(AssignmentCategory.FRONTEND)
                .build();
    }

    public static AssignmentResponse createnewAssignmentResponse() {
        return AssignmentResponse.builder()
                .assignmentId(UUID.randomUUID())
                .developerId(UUID.randomUUID())
                .category("Backend")
                .description("Build api")
                .score(10)
                .title("Weekend test 1")
                .build();
    }




    public static AccountRequest updateAccountRequest() {
        return AccountRequest.builder().emailAddress(MOCK_UPDATED_EMAIL).build();
    }

    public static AccountResponse updateAccountResponse(UUID accountId) {
        return AccountResponse.builder().id(accountId).emailAddress(MOCK_UPDATED_EMAIL).build();
    }

    public static DeveloperEntity createNewDeveloperEntity() {
        return DeveloperEntity.builder()
                .id(UUID.randomUUID())
                .emailAddress(MOCK_EMAIL)
                .build();
    }

    public static DeveloperEntity createNewDeveloperEntity(UUID developerId) {
        return DeveloperEntity.builder()
                .id(developerId)
                .emailAddress(MOCK_EMAIL)
                .build();
    }

}

