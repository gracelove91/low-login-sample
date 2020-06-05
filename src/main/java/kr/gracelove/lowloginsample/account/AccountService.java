package kr.gracelove.lowloginsample.account;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by GraceLove
 * Github  : https://github.com/gracelove91
 * Blog    : https://gracelove91.tistory.com
 * Email   : govlmo91@gmail.com
 *
 * @author : Eunmo Hong
 * @since : 2020/06/05
 */

@Transactional
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void validAccountInformation(AccountDto account) {
        accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword())
                .orElseThrow(RuntimeException::new);
    }

}
