package cn.edu.nju.config;

import cn.edu.nju.security.CustomTokenConverter;
import cn.edu.nju.security.CustomTokenServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;

/**
 * @author hongchuanwang
 * 必须有 Configuration，否者 configure 不成功
 */
@EnableAuthorizationServer
@Configuration
public class AuthConfig extends AuthorizationServerConfigurerAdapter {
    private AuthenticationManager authenticationManager;
    private RedisConnectionFactory redisConnectionFactory;
    private DataSource dataSource;

    @Autowired
    public AuthConfig(AuthenticationManager authenticationManager, RedisConnectionFactory redisConnectionFactory, DataSource dataSource) {
        this.authenticationManager = authenticationManager;
        this.redisConnectionFactory = redisConnectionFactory;
        this.dataSource = dataSource;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        //允许所有人请求令牌
        //已验证的客户端才能请求check_token端点
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetails());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager)
                .tokenServices(customTokenServices())
                .tokenStore(redisTokenStore())
                .accessTokenConverter(accessTokenConverter())
                // 重复使用 refresh_token 而不是刷新过 token 后连带刷新 refresh_token，只有 refresh_token 过期的时候才会刷新
                .reuseRefreshTokens(false);
    }

    private AuthorizationServerTokenServices customTokenServices() {
        CustomTokenServices tokenServices = new CustomTokenServices();
        tokenServices.setTokenStore(redisTokenStore());
        tokenServices.setSupportRefreshToken(true);
        // 设置重用 refresh_token 为 false，否则根据 refresh_token 获取 access_token 时的 refresh_token 会返回新的，是个 bug
        // 可以查看 issue：https://github.com/spring-projects/spring-security-oauth/issues/1193
        tokenServices.setReuseRefreshToken(false);
        tokenServices.setTokenEnhancer(accessTokenConverter());
        tokenServices.setClientDetailsService(clientDetails());
        return tokenServices;
    }

    /**
     * 客户端信息配置在数据库
     *
     * @return 返回数据库中客户端信息
     */
    @Bean
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }

    @Bean("redisTokenStore")
    public TokenStore redisTokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new CustomTokenConverter();
        converter.setSigningKey("key");
        return converter;
    }
}
