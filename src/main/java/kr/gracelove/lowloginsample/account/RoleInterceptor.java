package kr.gracelove.lowloginsample.account;


import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.annotation.Annotation;

/**
 * Created by GraceLove
 * Github  : https://github.com/gracelove91
 * Blog    : https://gracelove91.tistory.com
 * Email   : govlmo91@gmail.com
 *
 * @author : Eunmo Hong
 * @since : 2020/06/05
 */

@Component
public class RoleInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod method = (HandlerMethod) handler;
        RoleCheck roleCheck = method.getMethodAnnotation(RoleCheck.class);

        if (roleCheck == null) {
            return true;
        }


        HttpSession session = request.getSession();
        Object auth = session.getAttribute("auth");
        AccountDto sessionAuth = (AccountDto) auth;

        isValidRole(roleCheck, sessionAuth);

        return true;
    }

    private void isValidRole(RoleCheck roleCheck, AccountDto sessionAuth) {
        if (sessionAuth == null) {
            throw new AccessDeniedException("접근할 수 없는 권한.");
        }

        for (AccountRole role : roleCheck.role()) {
            if(sessionAuth.getRole().contains(role)) {
                return;
            }
        }
        throw new AccessDeniedException("접근할 수 없는 권한.");
    }
}
