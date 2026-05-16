package com.kosmo.admin.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDTO {

    private Long id;                   // 사용자 고유 ID
    private String username;           // 사용자 이름
    private String nickname;           // 닉네임 (선택 사항)
    private String phoneNumber;        // 연락처 (선택 사항)
    private String name;               // 이름
    private String email;              // 이메일
    private String gender;             // 성별
    private String  role;
    private boolean termsAgreement;    // 정보 동의 여부
    private boolean marketingAgreement; // 마케팅 동의 여부
    private LocalDateTime createdAt;    // 가입일
    private LocalDateTime updatedAt;    // 수정일

    // 날짜 포맷팅 메서드
    public String getCreatedAtFormatted() {
        return createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getUpdatedAtFormatted() {
        return updatedAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
