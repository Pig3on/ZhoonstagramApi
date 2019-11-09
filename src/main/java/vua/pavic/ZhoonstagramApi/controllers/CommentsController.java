package vua.pavic.ZhoonstagramApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import vua.pavic.ZhoonstagramApi.model.Comment;
import vua.pavic.ZhoonstagramApi.model.Post;
import vua.pavic.ZhoonstagramApi.services.CommentService;
import vua.pavic.ZhoonstagramApi.services.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentsController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/post/{id}")
    public List<Comment> getByPost(@PathVariable long id){
        return commentService.getCommentsByPostId(id);
    }
    @GetMapping("/user/{id}")
    public List<Comment> getByUser(@PathVariable long id){
        return commentService.getCommentsByUserId(id);
    }
    @GetMapping("/{id}")
    public Comment get(@PathVariable long id){
        return commentService.getCommentById(id);
    }
    @PostMapping
    public Comment post(@RequestBody Comment p){
        return commentService.updateOrAddComment(p);
    }
    @PutMapping
    public Comment put(@RequestBody Comment p){
        return commentService.updateOrAddComment(p);
    }
}
