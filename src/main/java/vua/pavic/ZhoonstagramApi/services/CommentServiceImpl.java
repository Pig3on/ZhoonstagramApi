package vua.pavic.ZhoonstagramApi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vua.pavic.ZhoonstagramApi.db.CommentRepository;
import vua.pavic.ZhoonstagramApi.db.PostRepository;
import vua.pavic.ZhoonstagramApi.db.UserRepository;
import vua.pavic.ZhoonstagramApi.model.Comment;
import vua.pavic.ZhoonstagramApi.model.Post;
import vua.pavic.ZhoonstagramApi.model.User;

import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> getCommentsByPostId(long id) {
        Post p = postRepository.getOne(id);
        return commentRepository.findAllByPost(p);
    }

    @Override
    public Comment getCommentById(long id) {
       return commentRepository.getOne(id);
    }

    @Override
    public Comment updateOrAddComment(Comment comment) {
        if(comment.getId() == 0) {
            return commentRepository.save(comment);
        }else {
            commentRepository.update(comment);
            return comment;
        }

    }

    @Override
    public void deleteComment(long id) {
        commentRepository.delete(id);
    }

}
