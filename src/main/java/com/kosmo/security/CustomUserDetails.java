package com.kosmo.security;

import lombok.Getter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

@Getter
public class CustomUserDetails extends User {

    // ID getter 추가
    private Long id;  // 사용자 ID 필드 추가

    // Constructor
    public CustomUserDetails(Long id, String username, String password, String role) {
        super(username, password, AuthorityUtils.createAuthorityList("ROLE_" + role));  // "ROLE_" 접두사 추가
        this.id = id;  // 사용자 ID 설정
    }

    // 사용자 역할 반환
    public String getRole() {
        return getAuthorities().isEmpty() ? null : getAuthorities().iterator().next().getAuthority();  // ROLE_ 접두사가 붙은 권한 반환
    }
}
