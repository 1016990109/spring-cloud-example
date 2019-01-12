package cn.edu.nju.security;

import cn.edu.nju.api.UserServerClient;
import cn.edu.nju.dto.UserDTO;
import cn.edu.nju.form.LoginFormBuilder;
import cn.edu.nju.security.bean.CustomAuthenticationToken;
import cn.edu.nju.security.bean.CustomUser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author hongchuanwang
 * 自定义的 AuthenticationProvider，实现向 user-server 发送验证密码的请求来达到鉴权目的
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider, InitializingBean, MessageSourceAware {
    private final Log logger = LogFactory.getLog(this.getClass());
    /**
     * 可考虑缓存用户
     */
    private MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

    private UserServerClient userServerClient;

    @Autowired
    public CustomAuthenticationProvider(UserServerClient userServerClient) {
        this.userServerClient = userServerClient;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.isInstanceOf(UsernamePasswordAuthenticationToken.class, authentication, () -> this.messages.getMessage("CustomAuthenticationProvider.onlySupports", "Only UsernamePasswordAuthenticationToken is supported"));

        String username = authentication.getPrincipal() == null ? "NONE_PROVIDED" : authentication.getName();
        if (authentication.getCredentials() == null) {
            this.logger.debug("Authentication failed: no credentials provided");
            throw new BadCredentialsException(this.messages.getMessage("CustomAuthenticationProvider.badCredentials", "Bad credentials"));
        } else {
            UserDTO user = userServerClient.checkUserPassword(new LoginFormBuilder().setUsername(username).setPassword(authentication.getCredentials().toString()).createLoginForm());
            if (user == null) {
                this.logger.debug("Authentication failed: password does not match stored value");
                throw new BadCredentialsException(this.messages.getMessage("CustomAuthenticationProvider.badCredentials", "Bad credentials"));
            }

            CustomUser customUser = new CustomUser.CustomUserBuilder()
                    .setId(user.getId())
                    .setPermissions(user.getPermissions())
                    .setUsername(user.getUsername())
                    .setPassword((String) authentication.getCredentials())
                    .setAuthorities(AuthorityUtils.createAuthorityList("USER"))
                    .createCustomUser();

            return new CustomAuthenticationToken(customUser);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(this.messages, "A message source must be set");
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messages = new MessageSourceAccessor(messageSource);
    }
}
