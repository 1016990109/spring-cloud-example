package cn.edu.nju.form;

import lombok.Data;

/**
 * @author hongchuanwang
 */
@Data
public class AddUserForm {
    private String username;
    private String password;
    private String nickname;
    private Byte gender;
    private String email;
    private String phone;
    private Integer status;
    private String description;
    private String avatar;
}
