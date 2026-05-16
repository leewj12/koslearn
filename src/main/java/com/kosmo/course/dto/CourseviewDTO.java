package com.kosmo.course.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//강의 일부목록보기
@Getter
@Setter
@NoArgsConstructor
@ToString
public class CourseviewDTO {

    private int course_id;
    private String title;
    private String nickname;
    private int price;

    private String summary;
    private String image;  // 이미지 경로 추가

    private Double averageCourseRating;  // 강의 별점 평균





}
