package com.aivle.bookapp.controller;

import com.aivle.bookapp.dto.review.ReviewCreateRequest;
import com.aivle.bookapp.dto.review.ReviewResponse;
import com.aivle.bookapp.dto.review.ReviewUpdateRequest;
import com.aivle.bookapp.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/books/{bookId}/reviews")
    public List<ReviewResponse> getReviewsByBook(@PathVariable Long bookId) {
        return reviewService.getReviewsByBook(bookId);
    }

    @GetMapping("/books/{bookId}/reviews/rating")
    public Double getAverageRating(@PathVariable Long bookId) {
        return reviewService.getAverageRating(bookId);
    }

    @PostMapping("/books/{bookId}/reviews")
    public ReviewResponse createReview(
            @PathVariable Long bookId,
            @RequestBody ReviewCreateRequest request
    ) {
        return reviewService.createReview(bookId, request);
    }

    @PatchMapping("/reviews/{reviewId}")
    public ReviewResponse updateReview(
            @PathVariable Long reviewId,
            @RequestBody ReviewUpdateRequest request
    ) {
        return reviewService.updateReview(reviewId, request);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public void deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
    }

    @PatchMapping("/reviews/{reviewId}/like")
    public ReviewResponse increaseLike(@PathVariable Long reviewId) {
        return reviewService.increaseLike(reviewId);
    }
}