package com.kosmo.instructor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class GetUserDTO {
    private Long userId;  // userId는 숫자로 처리
    private String username;
    private String nickname;
    private String name;
    private String phone_number;
    private String email;
    private String gender;
    private LocalDateTime createdAt;

    // createdAt을 로컬 시간대에 맞게 포맷한 문자열로 반환하는 메서드
    public String getFormattedCreatedAt() {
        if (createdAt != null) {
            // 날짜를 특정 형식으로 포맷
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
            return createdAt.format(formatter);
        }
        return "";
    }
}
