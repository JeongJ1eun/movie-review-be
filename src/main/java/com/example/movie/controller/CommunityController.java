package com.example.movie.controller;

import com.example.movie.model.Post;
import com.example.movie.service.CommunityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/community")
public class CommunityController {

    private final CommunityService communityService;

    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    // 모든 게시글 조회
    @GetMapping
    public List<Post> getAllPosts() {
        return communityService.getAllPosts();
    }

    // 정렬된 게시글 조회
    @GetMapping("/sorted/{sortType}")
    public List<Post> getSortedPosts(@PathVariable String sortType) {
        return communityService.getSortedPosts(sortType);
    }

    // 게시글 추가
    @PostMapping
    public Post addPost(@RequestBody Post post) {
        return communityService.addPost(post);
    }

    // 좋아요 증가
    @PostMapping("/{id}/like")
    public void likePost(@PathVariable Long id) {
        communityService.likePost(id);
    }

    // 싫어요 증가
    @PostMapping("/{id}/dislike")
    public void dislikePost(@PathVariable Long id) {
        communityService.dislikePost(id);
    }
}