package vua.pavic.ZhoonstagramApi.services;

import org.springframework.stereotype.Service;
import vua.pavic.ZhoonstagramApi.model.User;

@Service
public interface UserService {

    User getUserByid(long id);
    User getUserByEmail(String email);
}
