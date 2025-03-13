package com.example.movie.service;

import com.example.movie.model.Post;
import com.example.movie.repository.PostRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommunityService {

    private final PostRepository postRepository;

    public CommunityService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public List<Post> getSortedPosts(String sortType) {
        return switch (sortType) {
            case "likes" -> postRepository.findAllByOrderByLikesDesc();
            case "dislikes" -> postRepository.findAllByOrderByDislikesDesc();
            case "comments" -> postRepository.findAllByOrderByCommentsDesc();
            default -> postRepository.findAll();
        };
    }

    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    public void likePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setLikes(post.getLikes() + 1);
        postRepository.save(post);
    }

    public void dislikePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setDislikes(post.getDislikes() + 1);
        postRepository.save(post);
    }
}