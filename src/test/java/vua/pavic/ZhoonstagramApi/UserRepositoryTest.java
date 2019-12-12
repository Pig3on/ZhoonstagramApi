package vua.pavic.ZhoonstagramApi;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import vua.pavic.ZhoonstagramApi.db.PostRepository;
import vua.pavic.ZhoonstagramApi.db.UserRepository;
import vua.pavic.ZhoonstagramApi.model.Post;
import vua.pavic.ZhoonstagramApi.model.User;
import vua.pavic.ZhoonstagramApi.model.UserRoleEnum;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;


import javax.persistence.EntityManager;
import javax.sql.DataSource;
import javax.swing.text.html.Option;
import java.util.Optional;

@DataJpaTest
@RunWith(SpringRunner.class)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void testGetPost() {
        User user = new User(1);
        Optional<User> foundUser = userRepository.findById(user.getId());
        assertNotNull(foundUser);
        assertThat(foundUser.isPresent());
        assertEquals(user.getId(), foundUser.get().getId());
    }
    @Test
    public void testSavePost() {
        User user = new User();
        user.setEmail("email@email.com");
        user.setHash("some hash");
        user.setRole(UserRoleEnum.ADMIN);
        user.setEnabled(true);

        User foundPost = userRepository.save(user);
        assertNotNull(foundPost);
        assertNotNull(user.getId());
    }
    @Test
    public void testUpdatePost() {
        Optional<User> u = userRepository.findById(1L);
        assertNotNull(u);
        assertThat(u.isPresent());
        String unchangedEmail = u.get().getEmail();
        u.get().setEmail("some other email");
        userRepository.save(u.get());
        Optional<User> updatedUser = userRepository.findById(u.get().getId());
        assertNotNull(updatedUser);
        assertThat(updatedUser.isPresent());
        assertEquals(u.get().getId(),updatedUser.get().getId());
        assertNotEquals(unchangedEmail,updatedUser.get().getEmail());
    }

}