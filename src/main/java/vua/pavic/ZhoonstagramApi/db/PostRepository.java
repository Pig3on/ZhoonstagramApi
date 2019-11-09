package vua.pavic.ZhoonstagramApi.db;

import org.springframework.data.jpa.repository.JpaRepository;
import vua.pavic.ZhoonstagramApi.model.Post;
import vua.pavic.ZhoonstagramApi.model.User;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findAllByUser(User u);

}
