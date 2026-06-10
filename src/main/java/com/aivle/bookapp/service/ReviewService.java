package com.aivle.bookapp.service;

import com.aivle.bookapp.domain.Book;
import com.aivle.bookapp.domain.Review;
import com.aivle.bookapp.dto.review.ReviewCreateRequest;
import com.aivle.bookapp.dto.review.ReviewResponse;
import com.aivle.bookapp.dto.review.ReviewUpdateRequest;
import com.aivle.bookapp.repository.BookRepository;
import com.aivle.bookapp.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;

    // 특정 도서 리뷰 조회
    public List<ReviewResponse> getReviewsByBook(Long bookId) {
        return reviewRepository.findByBookIdOrderByCreatedAtDesc(bookId)
                .stream()
                .map(ReviewResponse::from)
                .toList();
    }

    // 리뷰 등록
    @Transactional
    public ReviewResponse createReview(Long bookId, ReviewCreateRequest request) {
        validateRating(request.getRating());

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("id가 " + bookId + "인 도서가 없습니다."));

        Review review = Review.builder()
                .book(book)
                .content(request.getContent())
                .rating(request.getRating())
                .likeCount(0)
                .build();

        Review savedReview = reviewRepository.save(review);

        return ReviewResponse.from(savedReview);
    }

    // 리뷰 수정
    @Transactional
    public ReviewResponse updateReview(Long reviewId, ReviewUpdateRequest request) {
        validateRating(request.getRating());

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("id가 " + reviewId + "인 리뷰가 없습니다."));

        review.update(request.getContent(), request.getRating());

        return ReviewResponse.from(review);
    }

    // 리뷰 삭제
    @Transactional
    public void deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("id가 " + reviewId + "인 리뷰가 없습니다."));

        reviewRepository.delete(review);
    }

    // 리뷰 좋아요 증가
    @Transactional
    public ReviewResponse increaseLike(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("id가 " + reviewId + "인 리뷰가 없습니다."));

        review.increaseLike();

        return ReviewResponse.from(review);
    }

    // 별점 검증
    private void validateRating(Integer rating) {
        if (rating == null || rating < 1 || rating > 5) {
            throw new RuntimeException("별점은 1점 이상 5점 이하만 가능합니다.");
        }
    }
}