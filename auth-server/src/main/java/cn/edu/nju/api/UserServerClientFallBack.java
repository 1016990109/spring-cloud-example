package cn.edu.nju.api;

import cn.edu.nju.dto.UserDTO;
import cn.edu.nju.form.LoginForm;
import org.springframework.stereotype.Component;

/**
 * @author hongchuanwang
 */
@Component
public class UserServerClientFallBack implements UserServerClient {

    @Override
    public UserDTO checkUserPassword(LoginForm loginForm) {
        return null;
    }
}
