package cn.edu.nju.security;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 自定义的密码加密方法，这里使用原生字符串（不加密）
 * ！！！推荐还是使用 BCryptPassEncoder
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
