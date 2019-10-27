package vua.pavic.ZhoonstagramApi.db;

import org.springframework.data.jpa.repository.JpaRepository;
import vua.pavic.ZhoonstagramApi.model.User;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);
}
