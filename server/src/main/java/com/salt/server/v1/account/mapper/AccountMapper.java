package com.salt.server.v1.account.mapper;

import com.salt.server.v1.account.api.dto.AccountDto;
import com.salt.server.v1.account.model.Account;

public class AccountMapper {

    public static AccountDto.Response toAccountResponse(Account account) {
        return new AccountDto.Response(
                account.getId(),
                account.getEmail(),
                account.getRole().toString()
        );
    }

}
