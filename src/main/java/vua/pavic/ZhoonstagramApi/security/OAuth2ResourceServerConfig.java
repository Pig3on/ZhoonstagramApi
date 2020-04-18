package vua.pavic.ZhoonstagramApi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    public OAuth2ResourceServerConfig(CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId("api");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .antMatchers("/swagger-resources**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/static/**").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/v2/**").permitAll()
                .antMatchers("/portal**").permitAll()
                .antMatchers("/portal/**").permitAll()
                .antMatchers("/api/signin**").permitAll()
                .antMatchers("/api/signin/**").permitAll()
                .antMatchers("/api/files**").permitAll()
                .antMatchers("/api/files/**").permitAll()
                .antMatchers("/api/comment/**").permitAll()
                .antMatchers("/api/post").permitAll()
                .antMatchers("/api/user").permitAll()
                .antMatchers("/api/post/**").permitAll()
                .antMatchers("/api/user/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint).accessDeniedHandler(new CustomAccessDeniedHandler());
    }
}
