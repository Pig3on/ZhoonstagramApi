package vua.pavic.ZhoonstagramApi.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @Column(name = "username",unique = true)
    private String email;
    @Column(name= "password")
    private String hash;
    private boolean enabled;
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

}
