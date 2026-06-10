package com.aivle.bookapp.repository;

import com.aivle.bookapp.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 특정 책에 달린 리뷰 목록 조회
    List<Review> findByBookIdOrderByCreatedAtDesc(Long bookId);

    // 특정 책의 리뷰 개수 조회
    long countByBookId(Long bookId);

    // 특정 책의 평균 별점 조회
    @Query("select avg(r.rating) from Review r where r.book.id = :bookId")
    Double findAverageRatingByBookId(Long bookId);
}