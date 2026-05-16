package com.kosmo.admin.dto;

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
public class AdminCourselistDTO {
    private Long courseId;
    private int userId;             // 사용자 ID
    private String title;           // 강의 제목
    private String description;     // 강의 설명
    private String summary;         // 강의 간단 설명
    private String url;             // 강의 URL
    private String thumbnailUrl;    // 썸네일 URL
    private String category;        // 강의 카테고리
    private String level;           // 강의 난이도
    private String image;           // UUID 파일명
    private String materials;       // UUID 첨부파일 리스트 (쉼표로 구분된 문자열)
    private String originalMaterials; // 원본 첨부파일 리스트 (쉼표로 구분된 문자열)
    private int price;              // 강의 가격
    private LocalDateTime createdAt; // createdAt을 LocalDateTime으로 변경


    public String getCreatedAtFormatted() {
        if (createdAt != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return createdAt.format(formatter);  // 원하는 포맷으로 변환하여 반환
        }
        return null;  // createdAt이 null일 경우 처리
    }




}
