package cn.edu.nju.dto;

import lombok.Data;

/**
 * @author hongchuanwang
 */
@Data
public class UserDTO {
    private Long id;
    private String email;
    private String phone;
    private String password;
    private Integer permissions;
}
