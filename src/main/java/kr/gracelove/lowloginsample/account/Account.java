package kr.gracelove.lowloginsample.account;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GraceLove
 * Github  : https://github.com/gracelove91
 * Blog    : https://gracelove91.tistory.com
 * Email   : govlmo91@gmail.com
 *
 * @author : Eunmo Hong
 * @since : 2020/06/05
 */

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Enumerated(EnumType.STRING)
    private AccountRole role;

    private String password;

    public Account(String username, String password, AccountRole role) {
        this.username = username;
        this.role = role;
        this.password = password;
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    protected Account() {
    }

    public String getPassword() {
        return password;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public AccountRole getRole() {
        return role;
    }
}
