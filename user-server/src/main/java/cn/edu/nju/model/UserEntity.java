package cn.edu.nju.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author hongchuanwang
 */
@Entity
@Data
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Integer status;
    private String description;
    private String avatar;
    private Timestamp createTime;
    private Timestamp modifyTime;
}
