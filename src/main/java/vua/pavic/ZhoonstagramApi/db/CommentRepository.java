package vua.pavic.ZhoonstagramApi.db;

import org.springframework.data.jpa.repository.JpaRepository;
import vua.pavic.ZhoonstagramApi.model.Comment;
import vua.pavic.ZhoonstagramApi.model.Post;
import vua.pavic.ZhoonstagramApi.model.User;

import java.util.List;

public interface CommentRepository {

    List<Comment> findAllByPost(Post p);
    Comment save(Comment c);
    Comment getOne(long id);
    void update(Comment c);
    void delete(long id);

}
