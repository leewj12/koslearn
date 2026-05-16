package com.kosmo.findpassword.controller;

import com.kosmo.findpassword.dto.FindPasswordDTO;
import com.kosmo.findpassword.service.FindPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@Controller
public class FindPasswordController {

    private final EmailService emailService;
    private final FindPasswordService findPasswordService;

    @Autowired
    public FindPasswordController(EmailService emailService, FindPasswordService findPasswordService) {
        this.emailService = emailService;
        this.findPasswordService = findPasswordService;
    }

    @GetMapping("/findpassword")
    public String findpassword(){
        return "member/findpassword";
    }

    @PostMapping("/findpassword")
    public String findpasswordCheck(FindPasswordDTO findPasswordDTO, HttpSession session, Model model) {
        String msg;
        String loc;

        // 사용자의 정보가 올바른지 확인
        boolean isValidUser = findPasswordService.checkUserInformation(findPasswordDTO);

        if (isValidUser) {
            // 인증번호 생성
            String verificationCode = emailService.generateVerificationCode();

            // 인증번호를 이메일로 보내기
            emailService.sendVerificationEmail(findPasswordDTO.getEmail(), verificationCode);

            // 세션에 인증번호 저장
            session.setAttribute("verificationCode", verificationCode);

            // 메시지 및 리다이렉트 위치 설정
            msg = "이메일로 인증번호가 전송되었습니다. 확인 부탁드립니다!";
            loc = "/findpassword/verify-code";

        } else {
            // 사용자 정보 불일치
            msg = "입력하신 정보가 일치하지 않습니다. 다시 시도해 주세요.";
            loc = "/findpassword";
        }

        // 모델에 메시지와 위치를 추가
        model.addAttribute("msg", msg);
        model.addAttribute("loc", loc);

        // 결과적으로 "/error/utility" 페이지를 반환하여 메시지와 리다이렉트 위치를 처리
        return "/error/utility";
    }


    // 인증번호 확인 페이지
    @GetMapping("/findpassword/verify-code")
    public String showVerifyCodePage() {
        return "/member/verify-code"; // 인증번호 입력 폼을 보여주는 페이지
    }

    // 인증번호 확인 및 비밀번호 변경
    @PostMapping("/findpassword/verify-code")
    public String verifyCodeAndRedirect(@RequestParam String verificationCode,
                                        @RequestParam String username,  // username 추가
                                        HttpSession session,
                                        Model model) {
        String storedVerificationCode = (String) session.getAttribute("verificationCode");

        String msg;
        String loc;

        // 인증번호 확인
        if (storedVerificationCode != null && storedVerificationCode.equals(verificationCode)) {
            // 인증번호가 맞으면 비밀번호 변경 페이지로 리다이렉트
            msg = "인증번호가 확인되었습니다. 비밀번호 변경 페이지로 이동합니다.";
            loc = "/findpassword/change-password?username=" + username;
        } else {
            // 인증번호 불일치 시
            msg = "인증번호가 일치하지 않습니다. 다시 시도해 주세요.";
            loc = "/findpassword/verify-code";  // 인증번호 재입력 페이지로 리다이렉트
        }

        // 모델에 메시지와 리다이렉트 위치 추가
        model.addAttribute("msg", msg);
        model.addAttribute("loc", loc);

        // 메시지와 리다이렉트 URL을 처리하는 페이지로 이동
        return "/error/utility";
    }



    @GetMapping("/findpassword/change-password")
    public String showChangePasswordPage(@RequestParam String username, Model model) {
        model.addAttribute("username", username);
        return "member/change-password";  // 비밀번호 변경 폼을 보여주는 페이지
    }

    @PostMapping("/findpassword/change-password")
    public String changePassword(@RequestParam String username,
                                 @RequestParam String newPassword,
                                 HttpSession session,
                                 Model model) {
        boolean isPasswordChanged = findPasswordService.changePassword(newPassword, username);

        String msg;
        String loc;

        if (isPasswordChanged) {
            // 비밀번호 변경 성공
            session.removeAttribute("verificationCode");  // 인증번호 세션에서 삭제
            msg = "비밀번호가 성공적으로 변경되었습니다. 로그인 페이지로 이동합니다.";
            loc = "/login";  // 비밀번호 변경 후 로그인 페이지로 리다이렉트
        } else {
            // 비밀번호 변경 실패
            msg = "비밀번호 변경에 실패했습니다. 먼저 아이디부터 찾기 하시기 바랍니다!.";
            loc = "/find-id";
        }

        // 모델에 메시지와 리다이렉트 위치 추가
        model.addAttribute("msg", msg);
        model.addAttribute("loc", loc);

        // 결과적으로 메시지와 리다이렉트 URL을 처리하는 페이지로 이동
        return "/error/utility";
    }


}
