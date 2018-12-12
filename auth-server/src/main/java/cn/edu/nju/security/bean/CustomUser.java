package cn.edu.nju.security.bean;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author hongchuanwang
 */
@Data
public class CustomUser extends User {
    private static final long serialVersionUID = 7868004030767905922L;

    private Long id;

    /**
     * 必须要有的构造函数
     * @param username
     * @param password
     * @param authorities
     */
    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
