package com.kosmo.review.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 수정된 ReviewInfoDTO
@Getter
@Setter
@NoArgsConstructor
public class ReviewInfoDTO {
    private Long courseId;  // course_id를 courseId로 수정
    private String title;
    private String description;
    private String summary;
    private String nickname;
}


