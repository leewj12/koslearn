package com.kosmo.community.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CommunityDTO {
    private int postId; // 게시글 번호
    private String title; // 제목
    private String category; // 카테고리
    private String content; // 내용
    private int userId; // 작성자 ID
    private int read_view; // 조회수
    private Long fileSize; // 파일 크기
    private String originFileName; // 원본 파일 이름
    private String uuidFileName; // 서버 저장 파일명 (UUID)
    private String mode; // 글쓰기(write), 수정(edit)
    private LocalDateTime createdAt; // 생성 시간
    private LocalDateTime updatedAt; // 수정 시간
    private String username;  // 작성자 이름

    public String getCreatedAtFormatted() {
        return createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public String getUpdatedAtFormatted() {
        if (updatedAt == null) {
            return ""; // 또는 원하는 기본 값으로 처리
        }
        return updatedAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
    public String getCreatedAtFormattedNew() {
        return createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

}
