package com.example.movie.repository;

import com.example.movie.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByLikesDesc(); // 좋아요 많은 순 정렬
    List<Post> findAllByOrderByDislikesDesc(); // 싫어요 많은 순 정렬
    List<Post> findAllByOrderByCommentsDesc(); // 댓글 많은 순 정렬
}