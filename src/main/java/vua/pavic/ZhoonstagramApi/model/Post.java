package vua.pavic.ZhoonstagramApi.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Data
@Entity
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
    private List<User> userLikes;
    @OneToMany(mappedBy = "post",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    private List<Comment> userComments;
}
