package com.chill.questapp.controllers;

import com.chill.questapp.entities.Comment;
import com.chill.questapp.request.CommentCreateRequest;
import com.chill.questapp.request.CommentUpdateRequest;
import com.chill.questapp.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private CommentService commentService;
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getAllComments(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId) {
        return commentService.getAllCommentsWithParam(userId,postId);
    }

    @GetMapping(path = "/{commentId}")
    public Comment getOneComment(@PathVariable Long commentId) {
        return commentService.getOneCommentById(commentId);
    }

    @PostMapping
    public Comment createOneComment(@RequestBody CommentCreateRequest commentCreateRequest) {
        return commentService.createOneComment(commentCreateRequest);
    }

    @PutMapping(path = "/{commnetId}")
    public Comment updateOneComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest commentUpdateRequest) {
        return commentService.updateOneCommentById(commentId,commentUpdateRequest);
    }

    @DeleteMapping(path = "/{commentId}")
    public void deleteOneComment(@PathVariable Long commentId) {
         commentService.deleteOneCommentById(commentId);
    }
}
