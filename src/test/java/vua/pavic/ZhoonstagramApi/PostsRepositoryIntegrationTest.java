package vua.pavic.ZhoonstagramApi;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import vua.pavic.ZhoonstagramApi.db.PostRepository;
import vua.pavic.ZhoonstagramApi.model.Post;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostsRepositoryIntegrationTest {

    @Autowired
    TestEntityManager testEntityManager;
    @Autowired
    PostRepository repository;

    @Test
    public void whenFindById_ReturnPost() {
        // given
        Post p = new Post(1);
        testEntityManager.persist(p);
        testEntityManager.flush();

        // when
        Post found = repository.findById(p.getId()).get();

        // then
        Assertions.assertThat(found.getId())
                .isEqualTo(p.getId());
    }
}
