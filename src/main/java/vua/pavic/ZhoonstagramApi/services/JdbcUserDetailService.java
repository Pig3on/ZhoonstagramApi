package vua.pavic.ZhoonstagramApi.services;

import org.bouncycastle.crypto.generators.BCrypt;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import vua.pavic.ZhoonstagramApi.db.UserRepository;
import vua.pavic.ZhoonstagramApi.model.User;

public class JdbcUserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);
        UserBuilder builder;
        if(user != null){
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(user.getHash());

        }else{
            throw new UsernameNotFoundException("User not found");
        }
       return builder.build();
    }
}
