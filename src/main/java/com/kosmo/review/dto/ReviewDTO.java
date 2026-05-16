package com.kosmo.review.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class ReviewDTO {
    private Long userId;
    private Long courseId;
    private String reviewText;
    private int rating;
    private int instructorRating;
}
