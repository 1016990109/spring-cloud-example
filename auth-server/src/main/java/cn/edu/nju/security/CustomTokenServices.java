package cn.edu.nju.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;

/**
 * @author hongchuanwang
 */
public class CustomTokenServices extends DefaultTokenServices {

    @Override
    public OAuth2AccessToken refreshAccessToken(String refreshTokenValue, TokenRequest tokenRequest) throws AuthenticationException {
        return super.refreshAccessToken(refreshTokenValue, tokenRequest);
    }
}
