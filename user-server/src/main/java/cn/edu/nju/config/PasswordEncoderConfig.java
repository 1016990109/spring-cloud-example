package cn.edu.nju.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author hongchuanwang
 */
@Component
public class PasswordEncoderConfig {
    private static final int ENCODE_STRENGTH = 4;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(ENCODE_STRENGTH);
    }
}
