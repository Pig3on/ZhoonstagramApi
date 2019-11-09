package vua.pavic.ZhoonstagramApi.services;

import org.springframework.stereotype.Service;
import vua.pavic.ZhoonstagramApi.model.Comment;
import vua.pavic.ZhoonstagramApi.model.Post;

import java.util.List;

@Service
public interface CommentService {

    List<Comment> getCommentsByUserId(long id);
    List<Comment> getCommentsByPostId(long id);
    Comment getCommentById(long id);
    Comment updateOrAddComment(Comment id);
    void deleteComment(long id);
}
