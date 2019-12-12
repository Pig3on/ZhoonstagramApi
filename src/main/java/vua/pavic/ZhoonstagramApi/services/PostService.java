package vua.pavic.ZhoonstagramApi.services;

import org.springframework.stereotype.Service;
import vua.pavic.ZhoonstagramApi.model.Post;

import java.util.List;

@Service
public interface PostService {

    List<Post> getAllPosts();
    List<Post> getPostsByUserId(long id);
    Post getPostById(long id);
    Post updateOrAddPost(Post id);
    void deletePost(long id);
    List<Post> getAllReportedPosts();
    void deleteMany(List<Post> posts);


}
