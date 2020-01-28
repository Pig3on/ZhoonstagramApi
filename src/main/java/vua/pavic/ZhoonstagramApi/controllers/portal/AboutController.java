package vua.pavic.ZhoonstagramApi.controllers.portal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/portal/about")
public class AboutController {

    @GetMapping
    public String index(){
        return "About";
    }
}
