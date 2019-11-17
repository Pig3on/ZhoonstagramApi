package vua.pavic.ZhoonstagramApi.controllers.portal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/portal/docs")
public class DocsController {
    @GetMapping
    public String index(){
        return "Docs";
    }
}
