package com.kosmo.error;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    // 로그인하지 않은 사용자가 들어올 경우 (인증 오류)
    @GetMapping("/custom-error")
    public String customError(Model model) {

        return "redirect:/custom-error/view";  // 리디렉션 경로 설정
    }
    @GetMapping("/custom-error/view")
    public String customErrorview(Model model){
        model.addAttribute("msg", "로그인이 필요합니다.");
        model.addAttribute("loc", "/login");  // 로그인 페이지로 리디렉션

    // /templates/error/nolong.html로 이동
        return "error/nolong";  // 로그인 페이지로 이동하는 뷰 이름
}


    // 권한이 없는 사용자가 들어올 경우 (권한 오류)
    @GetMapping("/accessDenied")
    public String accessDenied(Model model) {
        // 권한 메시지와 리디렉션 경로 설정
        return "error/noaccessDenied";  // 권한 오류 페이지로 이동하는 뷰 이름
    }
}