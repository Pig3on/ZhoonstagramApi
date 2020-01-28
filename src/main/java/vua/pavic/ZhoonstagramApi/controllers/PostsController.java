package vua.pavic.ZhoonstagramApi.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import vua.pavic.ZhoonstagramApi.model.Post;
import vua.pavic.ZhoonstagramApi.model.User;
import vua.pavic.ZhoonstagramApi.services.PostService;
import vua.pavic.ZhoonstagramApi.services.UserService;

import java.security.Principal;
import java.util.List;

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
    @GetMapping("/users/{id}")
    public List<Post> getByUser(@PathVariable long id){
        return postService.getPostsByUserId(id);
    }
    @GetMapping("/{id}")
    public Post get(@PathVariable long id){
        return postService.getPostById(id);
    }

    @PostMapping
    public Post post(Principal principal, @RequestBody Post p){
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
    @PutMapping
    public Post put(@RequestBody Post p){
        return postService.updateOrAddPost(p);
    }
}
