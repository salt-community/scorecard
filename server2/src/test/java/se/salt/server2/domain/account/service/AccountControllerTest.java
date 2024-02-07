package se.salt.server2.domain.account.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import se.salt.server2.domain.account.controller.dto.AccountController;
import se.salt.server2.domain.account.controller.dto.AccountRequest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountController accountController;

    @Autowired
    private ObjectMapper objectMapper;

    public static final String BASE_URL = "/api/v2/account";

    @Test
    @SneakyThrows
    void assertThatAccountIsCreated() {
        var request = AccountRequest.builder().emailAddress("yolo@hotmail.com").build();

        var response = mockMvc.perform(post(BASE_URL).contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(request))).andDo(print()).andReturn().getResponse();

        assertNotNull(response);
    }
}