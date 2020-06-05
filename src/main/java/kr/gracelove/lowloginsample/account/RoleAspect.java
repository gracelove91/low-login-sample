package kr.gracelove.lowloginsample.account;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;

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

@Aspect
@Component
public class RoleAspect {

    @Around("@annotation(roleCheck)")
    public Object RoleCheck(ProceedingJoinPoint pjp, RoleCheck roleCheck) throws Throwable {
        HttpSession session = null;

        for(Object o : pjp.getArgs()) {
            if (o instanceof HttpSession) {
                session = (HttpSession)o;
            }
        }

        AccountDto sessionAuth = null;
        if(session != null) {
            Object auth = session.getAttribute("auth");
            sessionAuth = (AccountDto) auth;
        }

        if(sessionAuth == null || !sessionAuth.getRole().equals(roleCheck.role())) {
            throw new AccessDeniedException("접근할 수 없는 권한.");
        }

        return pjp.proceed();
    }
}
