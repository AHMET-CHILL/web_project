package com.chill.questapp.controllers;

import com.chill.questapp.entities.Post;
import com.chill.questapp.request.PostCreateRequest;
import com.chill.questapp.request.PostUpdateRequest;
import com.chill.questapp.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/posts")
public class PostController {

    private  PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAllPosts(@RequestParam Optional<Long> userId) {

        return postService.getAllPosts(userId);
    }

    @GetMapping(path = "/{postId}")
    public Post getOnePost(@PathVariable Long postId) {
        return postService.getOnePostById(postId);
    }

    @PostMapping
    public Post createOnePost(@RequestBody PostCreateRequest newPostRequest ) {
        return postService.createOnePost(newPostRequest);
    }

    @PutMapping(path = "/{postId}")
    public Post updateOnePost(@PathVariable Long postId,@RequestBody PostUpdateRequest updatePostRequest ){
        return postService.updateOnePostById(postId,updatePostRequest);
    }

    @DeleteMapping(path = "/{postId}")
    public void deleteOnePost(@PathVariable Long postId){
        postService.deleteOnePost(postId);
    }



}
