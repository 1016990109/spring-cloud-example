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
    private Integer permissions;

    /**
     * 必须要有的构造函数
     * @param username
     * @param password
     * @param authorities
     */
    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public static class CustomUserBuilder {
        private Long id;
        private Integer permissions;
        private String username;
        private String password;
        private Collection<? extends GrantedAuthority> authorities;

        public CustomUserBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public CustomUserBuilder setPermissions(Integer permissions) {
            this.permissions = permissions;
            return this;
        }

        public CustomUserBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public CustomUserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public CustomUserBuilder setAuthorities(Collection<? extends GrantedAuthority> authorities) {
            this.authorities = authorities;
            return this;
        }

        public CustomUser createCustomUser() {
            CustomUser customUser = new CustomUser(username, password, authorities);
            customUser.setId(id);
            customUser.setPermissions(permissions);
            return customUser;
        }
    }
}
