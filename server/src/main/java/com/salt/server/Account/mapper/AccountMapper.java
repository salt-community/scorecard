package com.salt.server.Account.mapper;

import com.salt.server.Account.api.dto.AccountDto;
import com.salt.server.Account.model.Account;

public class AccountMapper {

    public static AccountDto.Response toAccountResponse(Account account) {
        return new AccountDto.Response(
                account.getId(),
                account.getEmail(),
                account.getRole().toString()
        );
    }

}
