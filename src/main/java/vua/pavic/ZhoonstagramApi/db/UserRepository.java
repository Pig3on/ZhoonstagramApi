package vua.pavic.ZhoonstagramApi.db;

import org.springframework.data.jpa.repository.JpaRepository;
import vua.pavic.ZhoonstagramApi.model.User;


public interface UserRepository {

    User findByEmail(String email);
    User findById(long id);
    User save(User user);
}
