package cn.edu.nju.api;

import cn.edu.nju.dto.UserDTO;
import cn.edu.nju.form.LoginForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author hongchuanwang
 */
@FeignClient(value = "user-server", fallback = UserServerClientFallBack.class)
public interface UserServerClient {
    /**
     * 调用 user-server 的 user-server/user/checkPassword
     * @param loginForm
     * @return
     */
    @PostMapping(value = "/user-server/user/password/check")
    UserDTO checkUserPassword(@RequestBody LoginForm loginForm);
}
