package vua.pavic.ZhoonstagramApi.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vua.pavic.ZhoonstagramApi.model.Post;
import vua.pavic.ZhoonstagramApi.model.User;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findAllByUser(User u);
    @Query("SELECT p FROM Post p WHERE p.reports > 15")
    List<Post> findReportedPosts();

}
