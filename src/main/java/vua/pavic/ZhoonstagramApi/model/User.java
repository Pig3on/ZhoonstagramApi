package vua.pavic.ZhoonstagramApi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class User {
    @Id
    public long id;
    public String email;
    public String hash;

    @OneToMany(targetEntity = UserRole.class)
    public List<UserRole> userRoles;

}
