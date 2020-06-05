package kr.gracelove.lowloginsample.account;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by GraceLove
 * Github  : https://github.com/gracelove91
 * Blog    : https://gracelove91.tistory.com
 * Email   : govlmo91@gmail.com
 *
 * @author : Eunmo Hong
 * @since : 2020/06/05
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final RoleInterceptor roleInterceptor;

    public WebConfig(RoleInterceptor roleInterceptor) {
        this.roleInterceptor = roleInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(roleInterceptor);
    }
}
