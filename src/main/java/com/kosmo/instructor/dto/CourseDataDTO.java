package com.kosmo.instructor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.cglib.core.Block;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CourseDataDTO {
    private Long courseId;
    private Long instructorId;      // 강사 ID
    private int userId;             // 사용자 ID
    private String title;           // 강의 제목
    private String description;     // 강의 설명
    private String summary;         // 강의 간단 설명
    private String url;             // 강의 URL
    private String thumbnailUrl;
    private String category;        // 강의 카테고리
    private String level;           // 강의 난이도
    private String image;           // UUID 파일명
    private String originalImageName; // 원본 이미지 파일명
    private long imageSize;         // 이미지 파일 크기
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




    // materials를 쉼표로 구분된 문자열로 변환하는 메서드
    public String getMaterialsString() {
        return materials;  // 이미 쉼표로 구분된 문자열로 처리되므로 그대로 반환
    }

    // originalMaterials를 쉼표로 구분된 문자열로 변환하는 메서드
    public String getOriginalMaterialsString() {
        return originalMaterials;  // 이미 쉼표로 구분된 문자열로 처리되므로 그대로 반환
    }
}
