package com.kosmo.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class UserAllDTO {
    private long id;
    private String username;  // 사용자 아이디
    private String password;  // 비밀번호
    private String name;  // 사용자 이름
    private String nickname;  // 닉네임 (선택 사항)
    private String phoneNumber;  // 연락처 (선택 사항)
    private String email;  // 이메일
    private String gender;  // 성별
    private boolean termsAgreement;  // 정보 동의 여부
    private boolean marketingAgreement;  // 마케팅 동의 여부
    private String role;  // 역할, 기본 값은 'student'
    private String origin_filename;
    private String hashing_filename;
    private long filesize;
    private long userId;
}
