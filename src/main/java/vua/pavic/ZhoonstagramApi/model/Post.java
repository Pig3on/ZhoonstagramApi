package vua.pavic.ZhoonstagramApi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
@Getter
@Setter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String picture;
    @NotNull(message = "{validation.noData}")
    private String description;
    @NotNull(message = "{validation.noData}")
    private String title;
    private long likes;
    private long reports;
    private long comments;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToMany(mappedBy = "likedPosts")
    @JsonIgnore
    private List<User> userLikes;
    @ManyToMany(mappedBy = "reportedPosts")
    @JsonIgnore
    private List<User> userReports;
    @OneToMany(mappedBy = "post",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    @JsonIgnore
    private List<Comment> userComments;

    public Post(long id) {
        this.id = id;
    }

    public Post() {
    }
}
