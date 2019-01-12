package cn.edu.nju.vo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author hongchuanwang
 */
@Data
public class UserVO {
    private Long userId;
    private String username;
    private String email;
    private String phone;
    private Integer status;
    private String description;
    private String avatar;
    private Timestamp createTime;
    private Timestamp modifyTime;
}
