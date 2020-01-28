package vua.pavic.ZhoonstagramApi.controllers.portal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@RestController
@RequestMapping("/portal/language")
public class LocaleController {

    @GetMapping("/{language}")
    public String index(@PathVariable String language, HttpServletRequest request, HttpServletResponse response){
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        localeResolver.setLocale(request, response,new Locale(language));

        return language;
    }
}
