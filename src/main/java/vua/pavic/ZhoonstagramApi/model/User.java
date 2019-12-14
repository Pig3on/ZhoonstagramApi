package vua.pavic.ZhoonstagramApi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "username")
    @NotNull
    @Email
    private String email;
    @Column(name= "password")
    @NotNull
    private String hash;
    private boolean enabled;
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;
    @OneToMany(mappedBy = "user",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    @JsonIgnore
    private List<Post> userPosts;
    @ManyToMany
    @JoinTable(name = "user_likes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name="post_id"))
    @JsonIgnore
    private  List<Post> likedPosts;
    @ManyToMany
    @JoinTable(name = "user_reports",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name="post_id"))
    @JsonIgnore
    private  List<Post> reportedPosts;
    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnore
    private List<Comment> userComments;

    public User(long id) {
        this.id = id;
    }

    public User() {
    }

}
