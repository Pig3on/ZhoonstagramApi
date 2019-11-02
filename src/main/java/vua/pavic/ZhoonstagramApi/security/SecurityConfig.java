package vua.pavic.ZhoonstagramApi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import vua.pavic.ZhoonstagramApi.security.JWTSupport.JWTAuthenticationFilter;
import vua.pavic.ZhoonstagramApi.security.JWTSupport.JWTLoginFilter;
import vua.pavic.ZhoonstagramApi.services.JdbcUserDetailService;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@EnableAutoConfiguration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public SecurityConfig(@Qualifier("dataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization","content-type"));
        configuration.setExposedHeaders(Arrays.asList("Authorization","content-type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    public void configure(WebSecurity web)  {
        web.ignoring().antMatchers(HttpMethod.POST,"/users/");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors();
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST,"/users/").permitAll()
                .antMatchers(HttpMethod.POST,"/managedMachines/register").permitAll()
                .antMatchers("/login").permitAll()
                .and().
                addFilterBefore(new JWTLoginFilter("/login"
                        ,authenticationManager()),UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(tokenFilterCreator(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .csrf().disable();

        http.exceptionHandling().authenticationEntryPoint(new Http403ForbiddenEntryPoint());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder());



    }



    @Bean
    public JWTAuthenticationFilter tokenFilterCreator(){

        RequestMatcher matcherRegister = new AntPathRequestMatcher("/users/","POST");
        RequestMatcher matcherLogin = new AntPathRequestMatcher("/login","POST");
        RequestMatcher matcherRegisterMachine = new AntPathRequestMatcher("/managedMachines/register","POST");
        RequestMatcher matcherJar = new AntPathRequestMatcher("/jmx/jmxSDK.jar","GET");

        RequestMatcher ignoreList = new OrRequestMatcher(matcherRegister,matcherLogin,matcherRegisterMachine,matcherJar);
        return new JWTAuthenticationFilter(ignoreList);
    }





}
