package vua.pavic.ZhoonstagramApi.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/posts")
public class PostsController {

    @GetMapping("/test")

    public String test() {
        return "test";
    }

    @GetMapping("/testAdmin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String testAdmin() {
        return "testAdmin";
    }
}
