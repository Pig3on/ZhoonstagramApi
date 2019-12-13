package vua.pavic.ZhoonstagramApi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vua.pavic.ZhoonstagramApi.db.PostRepository;
import vua.pavic.ZhoonstagramApi.db.UserRepository;
import vua.pavic.ZhoonstagramApi.model.Post;
import vua.pavic.ZhoonstagramApi.model.User;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Post> getAllPosts() {
       return postRepository.findAll();
    }

    @Override
    public List<Post> getPostsByUserId(long id) {
       User u =  userRepository.getOne(id);
       return postRepository.findAllByUser(u);
    }

    @Override
    public Post getPostById(long id) {
        return postRepository.getOne(id);
    }

    @Override
    public Post updateOrAddPost(Post post) {
       return postRepository.save(post);
    }

    @Override
    public void deletePost(long id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<Post> getAllReportedPosts() {
        return postRepository.findReportedPosts();
    }

    @Override
    public void deleteMany(List<Post> posts) {
        posts.forEach((post -> {
            postRepository.deleteById(post.getId());
        }));
    }
}
