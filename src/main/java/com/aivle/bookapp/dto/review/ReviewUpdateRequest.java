package com.aivle.bookapp.dto.review;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewUpdateRequest {

    private String content;
    private Integer rating;
}