package com.kosmo.notice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime; // LocalDateTime을 사용합니다.
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class NoticeDTO {
    private Long noticeid; // 게시글번호
    private int userId; // 작성자 ID
    private String username; // 작성자 이름
    private String title; // 공지 제목
    private String content; // 공지 내용

    // 썸네일 이미지 경로
    private String originalThumbnailImage; // 원본 썸네일 파일명
    private String hashedThumbnailImage;   // 해싱된 썸네일 파일명

    // 배너 이미지 경로
    private String originalBannerImage;    // 원본 배너 파일명
    private String hashedBannerImage; // 해싱된 배너 이미지 파일명

    private LocalDateTime createdAt; // 생성일 (LocalDateTime으로 수정)
    private int views_count; // 조회수

    public String getCreatedAtFormatted() {
        return createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

}

