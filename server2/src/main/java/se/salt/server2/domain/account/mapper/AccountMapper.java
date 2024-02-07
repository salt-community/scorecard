package se.salt.server2.domain.account.mapper;

import org.springframework.stereotype.Component;
import se.salt.server2.domain.account.controller.dto.AccountRequest;
import se.salt.server2.domain.account.controller.dto.AccountResponse;
import se.salt.server2.domain.account.models.AccountEntity;

@Component
public class AccountMapper {

    public AccountEntity mapToEntity(AccountRequest accountRequest) {
        return AccountEntity.builder().emailAddress(accountRequest.emailAddress()).build();
    }

    public AccountResponse mapToResponse(AccountEntity accountEntity) {
        return AccountResponse.builder().id(accountEntity.getId()).emailAddress(accountEntity.getEmailAddress()).build();
    }


}