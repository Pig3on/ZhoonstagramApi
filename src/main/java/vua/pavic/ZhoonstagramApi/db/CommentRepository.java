package vua.pavic.ZhoonstagramApi.db;

import org.springframework.data.jpa.repository.JpaRepository;
import vua.pavic.ZhoonstagramApi.model.Comment;
import vua.pavic.ZhoonstagramApi.model.Post;
import vua.pavic.ZhoonstagramApi.model.User;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findAllByUser(User u);
    List<Comment> findAllByPost(Post p);
}
