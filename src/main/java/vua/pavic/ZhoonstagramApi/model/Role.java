package vua.pavic.ZhoonstagramApi.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private int id;
    @Column(name = "role_name")
    private String roleName;
    @ManyToMany(mappedBy = "roles")
    List<User> users;
}
