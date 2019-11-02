package vua.pavic.ZhoonstagramApi.services;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vua.pavic.ZhoonstagramApi.customExceptions.UsernameExistsException;
import vua.pavic.ZhoonstagramApi.db.RoleRepository;
import vua.pavic.ZhoonstagramApi.db.UserRepository;
import vua.pavic.ZhoonstagramApi.model.Role;
import vua.pavic.ZhoonstagramApi.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcUserDetailsService implements JdbcUserDetailService {
    @Autowired
    private BCryptPasswordEncoder encoder;


    @Autowired
    private UserRepository appUserRepository;

    @Autowired
    private RoleRepository roleRepository;




    @Override
    public void registerNewUser(User appUser) throws UsernameExistsException {
        appUser.setHash(encoder.encode(appUser.getHash()));
        appUser.setEnabled(true);

        List<Role> roles = new ArrayList<>();
        Role r = roleRepository.findRoleByRoleName("ROLE_USER");
        roles.add(r);
        appUser.setRoles(roles);
        try{
            appUserRepository.save(appUser);
        }catch(Exception ex){
            this.handleUsernameExists(ex);
        }


    }

    private void handleUsernameExists(Throwable ex) throws UsernameExistsException {
        if(ex instanceof ConstraintViolationException){
            throw new UsernameExistsException("Username already exists");
        }
        if(ex == null){
            return;
        }
        handleUsernameExists(ex.getCause());
    }

    @Override
    public void addUpdateUser(User user) {
        appUserRepository.save(user);
    }

    @Override
    public User getUserByUsername(String email) {
        return appUserRepository.findByEmail(email);
    }

}
