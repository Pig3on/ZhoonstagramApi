package vua.pavic.ZhoonstagramApi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vua.pavic.ZhoonstagramApi.db.UserRepository;
import vua.pavic.ZhoonstagramApi.model.User;
import vua.pavic.ZhoonstagramApi.model.UserRoleEnum;
import vua.pavic.ZhoonstagramApi.model.api.ApiUser;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User getUserByid(long id) {
       return userRepository.getOne(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User addUser(ApiUser user) {
        User u = new User.UserBuilder()
                .withEmail(user.getEmail())
                .withPassword(passwordEncoder.encode(user.getPassword()))
                .asFreeUser()
                .build();
        return userRepository.save(u);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
