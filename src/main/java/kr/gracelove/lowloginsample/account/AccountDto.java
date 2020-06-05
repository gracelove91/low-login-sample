package kr.gracelove.lowloginsample.account;

import java.util.List;
import java.util.Objects;

/**
 * Created by GraceLove
 * Github  : https://github.com/gracelove91
 * Blog    : https://gracelove91.tistory.com
 * Email   : govlmo91@gmail.com
 *
 * @author : Eunmo Hong
 * @since : 2020/06/05
 */

public class AccountDto {

    private String username;
    private String password;
    private List<AccountRole> role;

    public AccountDto(String username, String password, List<AccountRole> role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    private AccountDto() {
    }

    public Account toEntity() {
        return new Account(username, password);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<AccountRole> getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountDto that = (AccountDto) o;
        return Objects.equals(username, that.username) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    @Override
    public String toString() {
        return "AccountDto{" +
                "username='" + username + '\'' +
                ", role=" + role +
                '}';
    }
}
