package vua.pavic.ZhoonstagramApi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String picture;
    private String description;
    private String title;
    private long likes;
    private long comments;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToMany(mappedBy = "likedPosts")
    @JsonIgnore
    private List<User> userLikes;
    @OneToMany(mappedBy = "post",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    @JsonIgnore
    private List<Comment> userComments;

    @Override
    public String toString() {
        return "";
    }
}
