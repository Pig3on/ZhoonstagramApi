package vua.pavic.ZhoonstagramApi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.AccessDeniedException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {


    @Autowired
    MessageSource messageSource;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        String message = messageSource.getMessage("error.notFound", null, LocaleContextHolder.getLocale());

        response.getWriter().write(message);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
