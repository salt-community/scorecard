package se.salt.server2.domain.account.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.salt.server2.domain.account.mapper.AccountMapper;
import se.salt.server2.domain.account.models.AccountEntity;
import se.salt.server2.domain.account.repository.AccountRepository;
import se.salt.server2.exception.AccountDoesNotExistException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static se.salt.server2.utils.TestData.createNewAccountEntity;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountMapper accountMapper;

    @InjectMocks
    private AccountService accountService;

    @Test
    void shouldThrowAccountNotFoundException() {
        AccountEntity entity = createNewAccountEntity();
        when(accountRepository.findById(entity.getId())).thenReturn(Optional.empty());

        assertThrows(AccountDoesNotExistException.class, () -> {
            accountService.getAccountById(entity.getId());
        });
    }
}
