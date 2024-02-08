package se.salt.server2.domain.account.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import se.salt.server2.domain.account.controller.dto.AccountRequest;
import se.salt.server2.domain.account.controller.dto.AccountResponses;
import se.salt.server2.domain.account.models.AccountEntity;
import se.salt.server2.domain.account.service.AccountService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static se.salt.server2.utils.TestData.*;

@WebMvcTest(AccountController.class)
class AccountControllerTest {

    @MockBean
    private AccountService accountService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @SneakyThrows
    void shouldReturnCreatedAccountAndReturn201Created() {
        AccountRequest request = createNewAccountRequest();

        when(accountService.createAccount(request)).thenReturn(createNewAccountResponse());

        mockMvc.perform(post(BASE_URL_ACCOUNT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.emailAddress").value(request.emailAddress()));
    }

    @Test
    @SneakyThrows
    void shouldReturnListOfAccountResponseAnd200_Ok() {
        AccountResponses responses = AccountResponses.builder()
                .accountResponseList(List.of(createNewAccountResponse(), createNewAccountResponse()))
                .build();
        when(accountService.getAllAccounts()).thenReturn(responses);

        mockMvc.perform(get(BASE_URL_ACCOUNT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountResponseList[0].emailAddress")
                        .value(responses.accountResponseList().get(0).emailAddress()));
    }

    @Test
    @SneakyThrows
    void shouldReturnSpecificAccountAnd200_Ok() {
        AccountEntity entity = createNewAccountEntity();
        when(accountService.getAccountById(entity.getId())).thenReturn(createNewAccountResponse(entity.getId()));

        mockMvc.perform(get(BASE_URL_ACCOUNT + "/{accountId}", entity.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(entity.getId().toString()));
    }
}