package vua.pavic.ZhoonstagramApi.security.JWTSupport;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import vua.pavic.ZhoonstagramApi.model.User;
import vua.pavic.ZhoonstagramApi.services.JdbcUserDetailService;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class   JWTLoginFilter extends AbstractAuthenticationProcessingFilter {



    public JWTLoginFilter(String url, AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, BadCredentialsException {
        ObjectMapper objMap = new ObjectMapper();
        objMap.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE,false);
        User cred = objMap.readValue(httpServletRequest.getInputStream(),User.class);

        AuthenticationManager manager = getAuthenticationManager();
        Authentication auth;

            auth = manager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            cred.getEmail(),cred.getHash(),Collections.emptyList()));
            return auth;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {


        JdbcUserDetailService userService;

        ServletContext servletContext = request.getServletContext();
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);

        userService = webApplicationContext.getBean(JdbcUserDetailService.class);

        JwtTokenUtil.addAuthentication(request,response,authResult.getName(),userService);

        response.setStatus(200);
        response.getWriter().flush();
        response.getWriter().close();
        return;

    }
}
