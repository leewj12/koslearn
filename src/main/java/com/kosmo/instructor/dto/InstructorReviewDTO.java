package com.kosmo.instructor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class InstructorReviewDTO {
    private int CourseCount;      // 강의 개수
    private Double instructorRating; // 강사 평점 평균
}
