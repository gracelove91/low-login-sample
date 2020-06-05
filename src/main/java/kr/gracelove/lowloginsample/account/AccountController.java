package kr.gracelove.lowloginsample.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;

/**
 * Created by GraceLove
 * Github  : https://github.com/gracelove91
 * Blog    : https://gracelove91.tistory.com
 * Email   : govlmo91@gmail.com
 *
 * @author : Eunmo Hong
 * @since : 2020/06/05
 */

@Controller
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/login")
    public String login(HttpSession session, @RequestBody AccountDto dto) {
        accountService.validAccountInformation(dto);
        session.setAttribute("auth", dto);
        return "redirect:index";
    }

    @GetMapping("/role1")
    @RoleCheck(role = AccountRole.ROLE1)
    public String role1(HttpSession session) {
        return "role1";
    }


    @GetMapping("/role2")
    @RoleCheck(role = AccountRole.ROLE2)
    public String role2(HttpSession session) {
        return "role2";
    }
}
