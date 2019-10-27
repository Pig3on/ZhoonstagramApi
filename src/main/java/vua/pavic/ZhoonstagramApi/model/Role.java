package vua.pavic.ZhoonstagramApi.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "authorities")
public class Role {
    @Id
    @Column(name = "username",unique = true)
    private String email;
    private String authority;
}
