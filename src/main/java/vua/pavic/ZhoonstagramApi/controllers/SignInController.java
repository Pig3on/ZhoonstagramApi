package vua.pavic.ZhoonstagramApi.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import vua.pavic.ZhoonstagramApi.db.UserRepository;
import vua.pavic.ZhoonstagramApi.model.User;
import vua.pavic.ZhoonstagramApi.model.UserRoleEnum;
import vua.pavic.ZhoonstagramApi.model.api.ApiUser;

@RestController
@RequestMapping("/api/signin")
public class SignInController {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    public SignInController(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    User signin(@RequestBody ApiUser user) {
        User u = new User();
        u.setEmail(user.getEmail());
        u.setHash(passwordEncoder.encode(user.getPassword()));
        u.setEnabled(true);
        u.setRole(UserRoleEnum.FREE);
        return repository.save(u);
    }
}
