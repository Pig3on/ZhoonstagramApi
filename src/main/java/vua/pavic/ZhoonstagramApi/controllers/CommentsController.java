package vua.pavic.ZhoonstagramApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;
import vua.pavic.ZhoonstagramApi.model.Comment;
import vua.pavic.ZhoonstagramApi.services.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentsController {

    @Autowired
    private CommentService commentService;

    private final JmsTemplate jmsTemplate;

    @Autowired
    public CommentsController(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @GetMapping("/post/{id}")
    public List<Comment> getByPost(@PathVariable long id){
        List<Comment>  comments = commentService.getCommentsByPostId(id);
        return comments;
    }
    @GetMapping("/{id}")
    public Comment get(@PathVariable long id){
        jmsTemplate.convertAndSend("The comment " + id + " get!");
        return commentService.getCommentById(id);
    }
    @PostMapping
    public Comment post(@RequestBody Comment p){
        jmsTemplate.convertAndSend("The comment " + p.getText() + " added!");
        return commentService.updateOrAddComment(p);
    }
    @PutMapping
    public Comment put(@RequestBody Comment p){
        jmsTemplate.convertAndSend("The comment " + p.getText() + " edited!");
        return commentService.updateOrAddComment(p);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        commentService.deleteComment(id);
    }


}
