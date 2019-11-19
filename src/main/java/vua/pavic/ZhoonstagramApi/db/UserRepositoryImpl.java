package vua.pavic.ZhoonstagramApi.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import vua.pavic.ZhoonstagramApi.model.User;
import vua.pavic.ZhoonstagramApi.model.UserRoleEnum;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {

    JdbcTemplate template;

    private final SimpleJdbcInsert userInserter;

    @Autowired
    public UserRepositoryImpl(JdbcTemplate template) {
        this.template = template;
        this.userInserter = new SimpleJdbcInsert(template)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public User findByEmail(String email) {
       return template.queryForObject("select id,email,hash,enabled,role from users where email = ?", this::mapRowToExercise ,email);
    }

    @Override
    public User findById(long id) {
        return template.queryForObject("select id,email,hash,enabled,role from users where id = ?", this::mapRowToExercise ,id);
    }

    @Override
    public User save(User user) {
        if(user.getId() == 0){
            user.setId(saveExerciseDetails(user));
        }
        else{
            updateUser(user);
        }

        return user;
    }

    private void updateUser(User user) {

        User userToUpdate = findById(user.getId());
        if(userToUpdate != null){
            this.template.update(
                    "update users set email=?, password=?, enabled=?, role=? where id = ?",
                    user.getEmail(),user.getHash(),user.isEnabled(),user.getRole().toString(),userToUpdate.getId());
        }

    }


    private User mapRowToExercise(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();

        user.setId(rs.getLong("id"));
        user.setEmail(rs.getString("email"));
        user.setHash(rs.getString("password"));
        user.setEnabled(rs.getBoolean("enabled"));
        user.setRole(UserRoleEnum.valueOf(rs.getString("role")));


        return user;
    }
    private long saveExerciseDetails(User exercise) {
        Map<String, Object> values = new HashMap<>();

        values.put("email", exercise.getEmail());
        values.put("password", exercise.getHash());
        values.put("enabled", exercise.isEnabled());
        values.put("role", exercise.getRole());


        return userInserter.executeAndReturnKey(values).longValue();
    }
}
