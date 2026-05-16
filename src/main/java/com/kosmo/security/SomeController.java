package com.kosmo.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SomeController {

    @GetMapping("/getUserId")
    public String getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // 인증된 사용자의 ID를 가져오기 (CustomUserDetails에서 getId() 사용)
            return ((CustomUserDetails) authentication.getPrincipal()).getId().toString();
        } else {
            return "guest";  // 인증되지 않은 사용자는 'guest'
        }
    }
}
