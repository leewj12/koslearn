package com.kosmo.course.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CourseAllviewDTO {
    // 강의 제목
    private String title;
    // 강의 설명 (상세한 설명)
    private String description;
    // 강의 간단 설명
    private String summary;
    // 강의 이미지  (강의 이미지 썸네일)
    private String image;
    // 강의 난이도 (예: 초급, 중급, 고급)
    private String level;
    // 강의 생성 일시 (타임스탬프, 자동 생성)
    private LocalDateTime createdAt;
    private String thumbnail; // 추가된 부분

    public String getCreatedAtFormatted() {
        if (createdAt != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return createdAt.format(formatter);  // 원하는 포맷으로 변환하여 반환
        }
        return null;  // 또는 ""로 반환할 수 있습니다.
    }


    // 강의 가격
    private int price;
    // 강의 카테고리
    private String category;
    // 강사의 경력
    private String biography;
    // 강사의 소개
    private String introduction;
    // 강사의 인사말
    private String greeting;
    // 강사의 이미지 파일명
    private String hashing_filename;
    // 강사의 이름 추가 (users 테이블에서 조회)
    private String instructorName;



}
