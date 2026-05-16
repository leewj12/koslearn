package com.kosmo.mypage.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PayDTO {
    private int course_id;  // 강의 ID
    private String title;    // 강의 제목
    private String image;    // 강의 이미지
    private String nickname;
}
