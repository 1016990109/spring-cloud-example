package cn.edu.nju.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author hongchuanwang
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.authorizeRequests()
                .requestMatchers(EndpointRequest.toAnyEndpoint())
                .permitAll()
                .and()
                .httpBasic() // 不使用 formLogin 方式，否则 user:password@host:port 登录方式不可用
                .and()
                .csrf().disable(); // 对 endpoint 的接口都禁用 csrf
    }
}
