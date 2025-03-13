package com.example.movie.controller;

import com.example.movie.model.Review;
import com.example.movie.service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // 특정 영화의 리뷰 조회
    @GetMapping("/{movieId}")
    public List<Review> getReviews(@PathVariable Long movieId) {
        return reviewService.getReviewsByMovie(movieId);
    }

    // 리뷰 추가
    @PostMapping
    public Review addReview(@RequestBody Review review) {
        return reviewService.addReview(review);
    }

    // 리뷰 삭제
    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
    }
}