package cn.edu.nju.security.bean;

import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * @author hongchuanwang
 */
public class CustomAuthenticationToken extends AbstractAuthenticationToken {
    private CustomUser userDetails;

    public CustomAuthenticationToken(CustomUser userDetails) {
        super(null);
        this.userDetails = userDetails;
        super.setAuthenticated(true);
    }

    @Override
    public Object getPrincipal() {
        return this.userDetails;
    }

    @Override
    public Object getCredentials() {
        return this.userDetails.getPassword();
    }

}