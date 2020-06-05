package kr.gracelove.lowloginsample.account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by GraceLove
 * Github  : https://github.com/gracelove91
 * Blog    : https://gracelove91.tistory.com
 * Email   : govlmo91@gmail.com
 *
 * @author : Eunmo Hong
 * @since : 2020/06/05
 */
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    AccountService instance;
    AccountRepository repository;

    @BeforeEach
    void setUp() {
        repository = mock(AccountRepository.class);
        instance = new AccountService(repository);
    }

    @Test
    @DisplayName("Account를 save한다.")
    void accountSave() {
        //given
        String username = "gracelove";
        String password = "password";
        AccountRole role = AccountRole.ROLE1;

        Account account = new Account(username, password, role);
        given(repository.save(account)).willReturn(account);

        //when
        Account findAccount = repository.save(account);

        //then
        assertEquals(username, findAccount.getUsername());
        assertEquals(role, findAccount.getRole());

        verify(repository).save(account);
    }


}