package com.kosmo.instructor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//저장용
@Getter
@Setter
@NoArgsConstructor
public class InstructorDTO {

    private String biography;         // 경력
    private String introduction;      // 소개
    private String greeting;          // 인사말
    private int userId;               // 사용자 ID (외래 키)
    private String origin_filename;
    private String hashing_filename;
    private long filesize;
}
