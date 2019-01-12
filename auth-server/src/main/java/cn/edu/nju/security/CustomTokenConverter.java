package cn.edu.nju.security;

import cn.edu.nju.constant.RequestHeaders;
import cn.edu.nju.security.bean.CustomUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author hongchuanwang
 */
public class CustomTokenConverter extends JwtAccessTokenConverter {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,
                                     OAuth2Authentication authentication) {
        CustomUser userDetails = (CustomUser) authentication.getPrincipal();
        // 默认的 access_token 中 additionalInformation 为空集合，且不能直接 put，故新建 map
        Map<String, Object> additionalInformation = new LinkedHashMap<>();
        additionalInformation.put(RequestHeaders.USER_ID, userDetails.getId());
        additionalInformation.put(RequestHeaders.PERMISSION, userDetails.getPermissions());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
        return super.enhance(accessToken, authentication);
    }

}
