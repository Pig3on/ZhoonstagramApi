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
import vua.pavic.ZhoonstagramApi.model.Post;
import vua.pavic.ZhoonstagramApi.model.User;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;


import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Optional;

@DataJpaTest
@RunWith(SpringRunner.class)
public class PostsRepositoryIntegrationTest {

    @Autowired
    PostRepository postRepository;

    @Test
    public void testGetPost() {
        Post post = new Post(1);
        Optional<Post> foundPost = postRepository.findById(post.getId());
        assertNotNull(foundPost);
        assertThat(foundPost.isPresent());
        assertEquals(post.getId(), foundPost.get().getId());
    }
    @Test
    public void testSavePost() {
        Post post = new Post();
        post.setTitle("Some title");
        post.setDescription("some description");
        post.setUser(new User(1));
        post.setDescription("some random description");
        post.setPicture("someTest.jpg");

        Post foundPost = postRepository.save(post);
        assertNotNull(foundPost);
        assertNotNull(post.getId());
    }
    @Test
    public void testUpdatePost() {

        Optional<Post> p = postRepository.findById(1L);

        assertNotNull(p);
        assertThat(p.isPresent());
        String unchangedDescription = p.get().getDescription();
        p.get().setDescription("something different");
        Post savedPost = postRepository.save(p.get());
        assertNotNull(savedPost);
        assertEquals(p.get().getId(),savedPost.getId());
        assertNotEquals(unchangedDescription,savedPost.getDescription());
    }

}