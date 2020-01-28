package vua.pavic.ZhoonstagramApi.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import vua.pavic.ZhoonstagramApi.db.UserRepository;
import vua.pavic.ZhoonstagramApi.model.User;
import vua.pavic.ZhoonstagramApi.model.UserRoleEnum;
import vua.pavic.ZhoonstagramApi.model.api.ApiUser;
import vua.pavic.ZhoonstagramApi.services.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("api/signin")
public class SignInController {

    private final UserService userService;


    public SignInController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    User post(@RequestBody ApiUser user) {
        return userService.addUser(user);
    }
}
