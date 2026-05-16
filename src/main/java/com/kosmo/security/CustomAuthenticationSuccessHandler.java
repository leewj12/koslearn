package com.kosmo.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        // "ROLE_ADMIN" 권한을 가진 사용자일 경우 관리자 대시보드가 아닌 다른 페이지로 리디렉션
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            response.sendRedirect("/admin/home");  // 관리자는 '/admin/home'으로 리디렉션
        } else {
            response.sendRedirect("/");  // 일반 사용자는 홈으로 리디렉션
        }
    }
}
