package vua.pavic.ZhoonstagramApi.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import vua.pavic.ZhoonstagramApi.model.Post;
import vua.pavic.ZhoonstagramApi.model.User;
import vua.pavic.ZhoonstagramApi.services.PostService;
import vua.pavic.ZhoonstagramApi.services.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/post")
public class PostsController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @GetMapping
    public List<Post> get(){
        return postService.getAllPosts();
    }
    @GetMapping("/{id}")
    public Post get(@PathVariable long id){
        return postService.getPostById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Post post(Principal principal,@Valid @RequestBody Post p){
        String pricipalName = principal.getName();
        User user = userService.getUserByEmail(pricipalName);

        p.setUser(user);

        Post postUpdate =  postService.updateOrAddPost(p);

        return postUpdate;
    }
    @PutMapping("/report/{postId}")
    public void reportPost (Principal principal, @PathVariable long postId){
        String email = principal.getName();
        postService.reportPost(email,postId);
    }
    @PostMapping("/users/id")
    public List<Post> getPostByUser(long userId) {
        List<Post> allPosts = postService.getAllPosts();
        return allPosts.stream().filter((post -> post.getUser().getId() == userId)).collect(Collectors.toList());
    }
    @PutMapping
    public Post put(@Valid @RequestBody Post p){
        return postService.updateOrAddPost(p);
    }
}
