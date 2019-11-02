package vua.pavic.ZhoonstagramApi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private long id;
    @Column(name = "username",unique = true)
    private String email;
    @Column(name= "password")
    private String hash;
    private boolean enabled;
    @ManyToMany(cascade = {
            CascadeType.ALL
    },fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_role")
    )
    @JsonIgnoreProperties(value = {"users"},allowSetters = true)
    private List<Role> roles;


}
