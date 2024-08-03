package com.chill.questapp.services;

import com.chill.questapp.entities.Comment;
import com.chill.questapp.entities.Post;
import com.chill.questapp.entities.User;
import com.chill.questapp.repos.CommentRepository;
import com.chill.questapp.request.CommentCreateRequest;
import com.chill.questapp.request.CommentUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private UserService userService;
    private PostService postService;

    public CommentService(CommentRepository commentRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<Comment> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {
        if (userId.isPresent() && postId.isPresent()) {
            return commentRepository.findByUserIdAndPostId(userId.get(),postId.get());
        } else if (userId.isPresent()) {
            return commentRepository.findByUserId(userId.get());

        } else if (postId.isPresent()) {
            return commentRepository.findByPostId(postId.get());

        }else
            return commentRepository.findAll();
    }

    public Comment getOneCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createOneComment(CommentCreateRequest request) {
        User user = userService.getOneUserById(request.getUserId());
        Post post = postService.getOnePostById(request.getPostId());
        if(user!=null && post!=null) {
            Comment commentToSave = new Comment();
            commentToSave.setId(request.getId());
            commentToSave.setPost(post);
            commentToSave.setUser(user);
            commentToSave.setText(request.getText());
            return commentRepository.save(commentToSave);
        }else
            return null;
    }


    public Comment updateOneCommentById(Long commnetId, CommentUpdateRequest request) {
        Optional<Comment> comment = commentRepository.findById(commnetId);
        if(comment.isPresent()) {
            Comment commentToUpdate = comment.get();
            commentToUpdate.setText(request.getText());
            return commentRepository.save(commentToUpdate);
        }else
            return null;
    }

    public void deleteOneCommentById(Long commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if(comment.isPresent()) {
            commentRepository.delete(comment.get());
        }else
            System.out.println("Comment not found");
    }
}
