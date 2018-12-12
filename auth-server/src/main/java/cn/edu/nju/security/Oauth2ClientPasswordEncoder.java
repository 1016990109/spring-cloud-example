package cn.edu.nju.security;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author hongchuanwang
 */
public class Oauth2ClientPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.toString().equals(encodedPassword);
    }

    public static PasswordEncoder getInstance() {
        return INSTANCE;
    }

    private static final PasswordEncoder INSTANCE = new Oauth2ClientPasswordEncoder();

    private Oauth2ClientPasswordEncoder() {
    }
}
