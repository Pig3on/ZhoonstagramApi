package vua.pavic.ZhoonstagramApi.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import vua.pavic.ZhoonstagramApi.model.Comment;
import vua.pavic.ZhoonstagramApi.model.Post;
import vua.pavic.ZhoonstagramApi.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Repository
public class CommentRepositoryImpl implements CommentRepository {

    JdbcTemplate template;
    SimpleJdbcInsert insertComment;

    @Autowired
    UserRepository userRepository;

    @Autowired
    public CommentRepositoryImpl(JdbcTemplate template) {
        this.template = template;
        this.insertComment = new SimpleJdbcInsert(template)
                .withTableName("comments")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public List<Comment> findAllByPost(Post p) {
        return template.query("select id, text, user_id, post_id from comments where post_id = ?", this::mapRowToComment, p.getId());
    }

    @Override
    public Comment save(Comment c) {
        c.setId(saveCommentDetails(c));
        return c;
    }

    @Override
    public Comment getOne(long id) {
        return template.queryForObject("select id, text, user_id, post_id from comments where id = ?", this::mapRowToComment, id);
    }

    private long saveCommentDetails(Comment comment) {
        Map<String, Object> values = new HashMap<>();

        values.put("text",comment.getText());
        values.put("user_id",comment.getUser().getId());
        values.put("post_id",comment.getPost().getId());

        return insertComment.executeAndReturnKey(values).longValue();
    }

    private Comment mapRowToComment(ResultSet rs, int rowNum) throws SQLException {
        Comment exercise = new Comment();

        exercise.setId(rs.getLong("id"));
        exercise.setText(rs.getString(("text")));
        exercise.setPost(new Post(rs.getLong("post_id")));
        Long id = rs.getLong("user_id");
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            exercise.setUser(user.get());
        }else{
            exercise.setUser(new User(rs.getLong("user_id")));
        }
        return exercise;
    }

}
