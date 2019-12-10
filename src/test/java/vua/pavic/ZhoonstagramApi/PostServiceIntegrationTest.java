package vua.pavic.ZhoonstagramApi;

import javafx.geometry.Pos;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import vua.pavic.ZhoonstagramApi.db.PostRepository;
import vua.pavic.ZhoonstagramApi.db.UserRepository;
import vua.pavic.ZhoonstagramApi.model.Post;
import vua.pavic.ZhoonstagramApi.services.PostService;
import vua.pavic.ZhoonstagramApi.services.PostServiceImpl;
import org.assertj.core.api.Assertions;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostServiceIntegrationTest {

    @TestConfiguration
    static class PostServiceImplTestContextConfiguration {
        @Bean
        public PostService employeeService() {
            return new PostServiceImpl();
        }
    }

    @Autowired
    private PostService employeeService;

    @MockBean
    private PostRepository employeeRepository;

    @Before
    public void setUp() {
        Post post = new Post(1);

        Mockito.when(employeeRepository.getOne(post.getId()))
                .thenReturn(post);
    }

    @Test
    public void whenValidId_thenPostShouldBeFound() {
        long id = 1;
        Post found = employeeService.getPostById(id);

        assertThat(found.getId())
                .isEqualTo(id);
    }

}
