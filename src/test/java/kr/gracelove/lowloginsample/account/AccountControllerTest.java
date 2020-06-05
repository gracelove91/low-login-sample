package kr.gracelove.lowloginsample.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by GraceLove
 * Github  : https://github.com/gracelove91
 * Blog    : https://gracelove91.tistory.com
 * Email   : govlmo91@gmail.com
 *
 * @author : Eunmo Hong
 * @since : 2020/06/05
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class AccountControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @Autowired
    AccountController instance;

    @MockBean
    AccountService accountService;

    @Test
    @DisplayName("로그인 테스트")
    void login() throws Exception {
        AccountDto account = new AccountDto("gracelove", "1234", List.of(AccountRole.USER));
        doNothing().when(accountService).validAccountInformation(account);

        mockMvc.perform(post("/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(account)))
                    .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("index"))
                .andExpect(request().sessionAttribute("auth", account));
    }

    //////////////////////////
    ////// user() 테스트 //////
    //////////////////////////
    @Test
    @DisplayName("user() 접근 시 AccountRole.ADMIN 접근 가능하다.")
    void roleAdminAccessAdminPage() throws Exception {
        AccountDto account = new AccountDto("gracelove", "1234", List.of(AccountRole.ADMIN));

        mockMvc.perform(get("/user")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(account))
                    .sessionAttr("auth", account))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("user"));
    }

    @Test
    @DisplayName("user() 접근 시 AccountRole.USER 접근 가능하다.")
    void roleUserAccessAdminPage() throws Exception {
        AccountDto account = new AccountDto("gracelove", "1234", List.of(AccountRole.USER));

        mockMvc.perform(get("/user")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(account))
                    .sessionAttr("auth", account))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("user"));
    }

    @Test
    @DisplayName("user() 접근 시 로그인안한 상태 접근 불가능하다.(CODE 403)")
    void anonymousAccessAdminPage() throws Exception {
        mockMvc.perform(get("/user"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }


    //////////////////////////
    ////// role2() 테스트 //////
    //////////////////////////

    @Test
    @DisplayName("admin() 접근 시 AccountRole.USER 접근 불가능하다.(CODE 403)")
    void roleAdminAccessUserPage() throws Exception {
        AccountDto account = new AccountDto("gracelove", "1234", List.of(AccountRole.USER));

        mockMvc.perform(get("/admin")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(account))
                    .sessionAttr("auth", account))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("admin() 접근 시 AccountRole.ADMIN 접근 가능하다.")
    void roleUserAccessUserPage() throws Exception {
        AccountDto account = new AccountDto("gracelove", "1234", List.of(AccountRole.ADMIN));

        mockMvc.perform(get("/admin")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(account))
                    .sessionAttr("auth", account))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("admin"));
    }

    @Test
    @DisplayName("admin() 접근 시 로그인안한 상태 접근 불가능하다.(CODE 403)")
    void anonymousAccessUserPage() throws Exception {
        mockMvc.perform(get("/admin"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

}