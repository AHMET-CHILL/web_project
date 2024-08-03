package com.chill.questapp.controllers;

import com.chill.questapp.entities.Like;
import com.chill.questapp.request.LikeCreateRequest;
import com.chill.questapp.responses.LikeResponse;
import com.chill.questapp.services.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/likes")
public class LikeController {

    private LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public List<LikeResponse> getAllLikes(@RequestParam Optional<Long> postId, @RequestParam Optional<Long> userId) {

        return likeService.getAllLikesWithParam(postId,userId);

    }

    @GetMapping(path = "/{likeId}")
    public Like getOneLike(@PathVariable Long likedId) {
        return likeService.getOneLikedById(likedId);

    }

    @PostMapping
    public Like createOneLike(@RequestBody LikeCreateRequest request) {
        return likeService.createOneLike(request);
    }

    @DeleteMapping(path = "/{likeId}")
    public void deleteOneLike(@PathVariable Long likeId) {
        likeService.deleteOneLikeById(likeId);
    }
}
