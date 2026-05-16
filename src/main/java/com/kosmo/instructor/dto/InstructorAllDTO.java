package com.kosmo.instructor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class InstructorAllDTO {
    private String username;
    private String name;
    private String nickname;
    private String phone_number;
    private String email;
    private String gender;
    private Date createdAt;

    // 강사 정보
    private String biography;
    private String introduction;
    private String greeting;
    private String origin_filename;
    private String hashing_filename;
    private long filesize;
}
