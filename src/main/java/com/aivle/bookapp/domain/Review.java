package com.aivle.bookapp.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 책 리뷰 1:N, REIVEW.book_id FK
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    // 1~5점 별점
    @Column(nullable = false)
    private double rating;

    @Column(name = "like_count", nullable = false)
    private Integer likeCount;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();

        if (this.likeCount == null) {
            this.likeCount = 0;
        }
    }

    public void update(String content, Double rating) {
        this.content = content;
        this.rating = rating;
    }

    public void increaseLike() {
        this.likeCount++;
    }
}
