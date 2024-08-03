package com.chill.questapp.services;

import com.chill.questapp.entities.Post;
import com.chill.questapp.entities.User;
import com.chill.questapp.repos.PostRepository;
import com.chill.questapp.request.PostCreateRequest;
import com.chill.questapp.request.PostUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;
    private UserService userService;


    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;

    }

    public List<Post> getAllPosts(Optional<Long> userId) {
        if (userId.isPresent()) {
            return postRepository.findByUserId(userId.get());
        }
        return postRepository.findAll();
    }

    public Post getOnePostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createOnePost(PostCreateRequest newPostRequest) {
        User user= userService.getOneUser(newPostRequest.getUserId());
        if (user== null)
            return null;
        Post toSave= new Post();
        toSave.setId(newPostRequest.getId());
        toSave.setText(newPostRequest.getText());
        toSave.setTitle(newPostRequest.getTitle());
        toSave.setUser(user);
        return postRepository.save(toSave);

    }

    public Post updateOnePostById(Long postId, PostUpdateRequest updatePostRequest) {
        Optional<Post> post = postRepository.findById(postId);

        if (post.isPresent()) {
            Post toUpdate = post.get();
            toUpdate.setText(updatePostRequest.getText());
            toUpdate.setTitle(updatePostRequest.getTitle());
            return postRepository.save(toUpdate);
        }
        return null;
    }


    public void deleteOnePost(Long postId) {

        postRepository.deleteById(postId);
    }
}
