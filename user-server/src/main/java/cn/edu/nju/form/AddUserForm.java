package cn.edu.nju.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author hongchuanwang
 */
@Data
public class AddUserForm {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private String email;
    private String phone;
    private String description;
    private String avatar;
}
