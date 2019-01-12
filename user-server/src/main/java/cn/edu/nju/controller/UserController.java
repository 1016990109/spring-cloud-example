package cn.edu.nju.controller;

import cn.edu.nju.bean.Response;
import cn.edu.nju.constant.RequestHeaders;
import cn.edu.nju.dto.UserDTO;
import cn.edu.nju.form.AddUserForm;
import cn.edu.nju.form.LoginForm;
import cn.edu.nju.form.ModifyUserPasswordForm;
import cn.edu.nju.service.UserService;
import cn.edu.nju.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author hongchuanwang
 */
@RestController
@RequestMapping("/user-server")
public class UserController {
    private static final String AT = "@";
    private static final int PHONE_LENGTH = 11;

    @Autowired
    private UserService userService;

    @PostMapping("/user/password/check")
    public UserDTO checkPassword(@RequestBody LoginForm loginForm) {
        // 邮箱
        if (loginForm.getUsername().contains(AT)) {
            return userService.checkPasswordByEmail(loginForm.getUsername(), loginForm.getPassword());
        }

        // 手机
        if (loginForm.getUsername().length() == PHONE_LENGTH) {
            return userService.checkPasswordByPhone(loginForm.getUsername(), loginForm.getPassword());
        }

        return null;
    }

    @PostMapping("/user/add")
    public Response<Boolean> addUser(@Validated @RequestBody AddUserForm form) {
        return new Response<>(userService.addUser(form));
    }

    @PostMapping("/user/password/modify")
    public Response<Boolean> modifyUserPassword(@Validated @RequestBody ModifyUserPasswordForm form) {
        return new Response<>(userService.modifyUserPassword(form));
    }

    @GetMapping("/user/detail")
    public Response<UserVO> getUserDetail(@RequestHeader(RequestHeaders.USER_ID) Long userId) {
        return new Response<>(userService.getUserDetail(userId));
    }
}
