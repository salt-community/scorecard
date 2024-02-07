package se.salt.server2.domain.account.mapper;

import org.junit.jupiter.api.Test;
import se.salt.server2.domain.account.controller.dto.AccountRequest;
import se.salt.server2.domain.account.controller.dto.AccountResponse;
import se.salt.server2.domain.account.controller.dto.AccountResponses;
import se.salt.server2.domain.account.models.AccountEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static se.salt.server2.utils.TestData.createNewAccountEntity;
import static se.salt.server2.utils.TestData.createNewAccountRequest;

class AccountMapperTest {
    private final AccountMapper accountMapper = new AccountMapper();

    @Test
    void shouldMapAccountRequestToEntity() {
        AccountRequest request = createNewAccountRequest();
        AccountEntity entity = accountMapper.mapToEntity(request);

        assertThat(entity).isInstanceOf(AccountEntity.class);
        assertThat(entity.getEmailAddress()).isEqualTo(request.emailAddress());
    }

    @Test
    void shouldMapAccountEntityToResponse() {
        AccountEntity entity = createNewAccountEntity();
        AccountResponse response = accountMapper.mapToResponse(entity);

        assertThat(response).isInstanceOf(AccountResponse.class);
        assertThat(entity.getEmailAddress()).isEqualTo(response.emailAddress());
    }

    @Test
    void shouldMapListOfEntitiesToListOfResponses() {
        List<AccountEntity> entities = List.of(createNewAccountEntity(), createNewAccountEntity());
        AccountResponses responses = accountMapper.mapToAccountResponses(entities);

        assertThat(responses.accountResponseList()).hasSize(2);
        assertThat(responses.accountResponseList().getFirst()).isInstanceOf(AccountResponse.class);
        assertEquals(
                entities.getFirst().getId(),
                responses.accountResponseList().getFirst().id());
    }
}