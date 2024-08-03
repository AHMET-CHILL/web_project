package com.chill.questapp.services;

import com.chill.questapp.entities.Like;
import com.chill.questapp.entities.Post;
import com.chill.questapp.entities.User;
import com.chill.questapp.repos.LikeRepository;
import com.chill.questapp.request.LikeCreateRequest;
import com.chill.questapp.responses.LikeResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeService {
    private final UserService userService;
    private final PostService postService;
    private LikeRepository likeRepository;

    public LikeService(LikeRepository likeRepository, UserService userService, PostService postService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<LikeResponse> getAllLikesWithParam(Optional<Long> postId, Optional<Long> userId) {
        List<Like> list;
        if (postId.isPresent() && userId.isPresent()) {
            list=likeRepository.findByUserIdAndPostId(userId.get(),postId.get());
        } else if (userId.isPresent()) {
            list=likeRepository.findByUserId(userId.get());

        } else if (postId.isPresent()) {
            list=likeRepository.findByPostId(postId.get());

        }else
            list=likeRepository.findAll();

        return list.stream().map(like-> new LikeResponse(like)).collect(Collectors.toList());
    }

    public Like getOneLikedById(Long likedId) {
        return likeRepository.findById(likedId).orElse(null);
    }

    public Like createOneLike(LikeCreateRequest request) {
        User user =userService.getOneUserById(request.getUserId());
        Post post=postService.getOnePostById(request.getPostId());
        if (user!=null && post!=null) {
            Like likeToSave=new Like();
            likeToSave.setId(request.getId());
            likeToSave.setUser(user);
            likeToSave.setPost(post);
            return likeRepository.save(likeToSave);
        }else
            return null;
    }

    public void deleteOneLikeById(Long likeId) {
         likeRepository.deleteById(likeId);
    }
}
