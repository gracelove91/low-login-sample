package kr.gracelove.lowloginsample.account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
@DataJpaTest
class AccountRepositoryTest {

    private static final String DEFAULT_USERNAME = "GRACELOVE";
    private static final String DEFAULT_PASSWORD = "1234";
    private static final AccountRole DEFAULT_ROLE = AccountRole.ADMIN;


    @Autowired
    AccountRepository repository;

    @BeforeEach
    @DisplayName("테스트 하기 전 Account 저장한다.")
    void setUp() {
        Account account = new Account(DEFAULT_USERNAME, DEFAULT_PASSWORD, List.of(DEFAULT_ROLE));
        repository.save(account);
    }

    @Test
    @DisplayName("findByUsername() 메서드로 가져온 계정 정보가 저장한 계정과 일치하는 지 확인한다.")
    void findByUsername() {
        Account account = repository.findByUsername(DEFAULT_USERNAME).orElseThrow(RuntimeException::new);

        assertEquals(DEFAULT_USERNAME, account.getUsername());
        assertEquals(DEFAULT_ROLE, account.getRole());
    }

//    @Test
//    @DisplayName("findByUsernameAndPassword() 메서드로 가져온 계정 정보가 저장한 계정과 일치하는 지 확인한다.")
//    void findByUsernameAndPassword() {
//        Account account = repository.findByUsernameAndPassword(DEFAULT_USERNAME, DEFAULT_PASSWORD).orElseThrow(RuntimeException::new);
//
//        assertEquals(DEFAULT_USERNAME, account.getUsername());
//        assertEquals(DEFAULT_PASSWORD, account.getPassword());
//        assertEquals(DEFAULT_ROLE, account.getRole());
//    }

}