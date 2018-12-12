package cn.edu.nju.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author hongchuanwang
 */
@Data
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String email;
    private String phone;
    private Integer permissions;
}
