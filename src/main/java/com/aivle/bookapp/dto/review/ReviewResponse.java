package com.aivle.bookapp.dto.review;

import com.aivle.bookapp.domain.Review;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReviewResponse {

    private Long id;
    private Long bookId;
    private String content;
    private Integer rating;
    private Integer likeCount;
    private LocalDateTime createdAt;

    public static ReviewResponse from(Review review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .bookId(review.getBook().getId())
                .content(review.getContent())
                .rating(review.getRating())
                .likeCount(review.getLikeCount())
                .createdAt(review.getCreatedAt())
                .build();
    }
}