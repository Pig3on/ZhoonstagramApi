package vua.pavic.ZhoonstagramApi.services;

import org.bouncycastle.crypto.generators.BCrypt;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vua.pavic.ZhoonstagramApi.customExceptions.UsernameExistsException;
import vua.pavic.ZhoonstagramApi.db.UserRepository;
import vua.pavic.ZhoonstagramApi.model.User;

public interface JdbcUserDetailService {
    void registerNewUser(User appUser) throws UsernameExistsException;

    void addUpdateUser(User user);
    User getUserByUsername(String username);
}
