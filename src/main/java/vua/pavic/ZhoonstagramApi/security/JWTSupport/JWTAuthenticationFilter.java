package vua.pavic.ZhoonstagramApi.security.JWTSupport;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;
import vua.pavic.ZhoonstagramApi.services.JdbcUserDetailService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationFilter extends GenericFilterBean {
    private RequestMatcher matcher;
    public JWTAuthenticationFilter(RequestMatcher matcher) {
        this.matcher = matcher;
    }

    @Override
    protected void initFilterBean() throws ServletException {
        super.initFilterBean();


    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        if(!matcher.matches(request)){
        JdbcUserDetailService userService;
        ServletContext servletContext = servletRequest.getServletContext();
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        userService = webApplicationContext.getBean(JdbcUserDetailService.class);

                Authentication authentication = JwtTokenUtil.getAuthentication((HttpServletRequest) servletRequest,userService);

                if(authentication != null){
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }else{

                    HttpServletResponse response = (HttpServletResponse)servletResponse;
                    response.setStatus(403);
                    response.getWriter().write("Invalid credentials");
                    response.getWriter().flush();
                    return;
                }




        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
