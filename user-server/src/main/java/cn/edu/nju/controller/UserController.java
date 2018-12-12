package cn.edu.nju.controller;

import cn.edu.nju.bean.Response;
import cn.edu.nju.form.AddUserForm;
import cn.edu.nju.form.ModifyUserPasswordForm;
import cn.edu.nju.service.UserService;
import cn.edu.nju.dto.UserDTO;
import cn.edu.nju.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(value = "/user/{email}")
    public UserDTO getUserByEmail(@PathVariable("email") String email) {
        return new UserDTO(1L, email, "18668775879", 1);
    }

    @PostMapping(value = "/user/password/check")
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

    @PostMapping(value = "/user/add")
    public Response<Boolean> addUser(@RequestBody AddUserForm form) {
        return new Response<>(userService.addUser(form));
    }

    @PostMapping(value = "/user/password/modify")
    public Response<Boolean> modifyUserPassword(@RequestBody ModifyUserPasswordForm form) {
        return new Response<>(userService.modifyUserPassword(form));
    }
}
