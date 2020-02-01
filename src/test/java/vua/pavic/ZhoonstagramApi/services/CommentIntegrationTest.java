package vua.pavic.ZhoonstagramApi.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import vua.pavic.ZhoonstagramApi.db.CommentRepository;
import vua.pavic.ZhoonstagramApi.db.PostRepository;
import vua.pavic.ZhoonstagramApi.model.Comment;
import vua.pavic.ZhoonstagramApi.model.Post;
import vua.pavic.ZhoonstagramApi.services.CommentService;
import vua.pavic.ZhoonstagramApi.services.CommentServiceImpl;
import vua.pavic.ZhoonstagramApi.services.PostService;
import vua.pavic.ZhoonstagramApi.services.PostServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentIntegrationTest {

    @TestConfiguration
    static class CommentServiceImplTestContextConfiguration {
        @Bean
        public CommentService employeeService() {
            return new CommentServiceImpl();
        }
    }

    @Autowired
    private CommentService commentService;

    @MockBean
    private CommentRepository commentRepository;
    @MockBean
    private PostRepository postRepository;

    @Before
    public void setUp() {
        Post post  = new Post(1);
        List<Comment> comments = new ArrayList<>();
        Comment c = new Comment();
        c.setPost(post);
        c.setPost(post);
        c.setPost(post);

        comments.add(c);
        comments.add(c);

        Mockito.when(postRepository.getOne(post.getId()))
                .thenReturn(post);

        Mockito.when(commentRepository.findAllByPost(post))
                .thenReturn(comments);
    }

    @Test
    public void whenValidId_thenPostShouldBeFound() {
        long id = 1;
        List<Comment> found = commentService.getCommentsByPostId(id);

        assertThat(found.size()).isEqualTo(2);
    }

}
