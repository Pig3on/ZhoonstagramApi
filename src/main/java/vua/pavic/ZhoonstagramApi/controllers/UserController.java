package vua.pavic.ZhoonstagramApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vua.pavic.ZhoonstagramApi.customExceptions.UsernameExistsException;
import vua.pavic.ZhoonstagramApi.model.User;
import vua.pavic.ZhoonstagramApi.services.JdbcUserDetailService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/users")
public class UserController {
    @Autowired
    JdbcUserDetailService services;

    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String successLogin() {
        return "YAY";
    }


    @PostMapping
    public String registerUser(@RequestBody User appUser) throws UsernameExistsException {

        //PasswordPolicyUtil.checkPassword(appUser);

        services.registerNewUser(appUser);
        return "registered";
    }

}
