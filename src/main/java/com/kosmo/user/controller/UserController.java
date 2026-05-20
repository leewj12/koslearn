package com.kosmo.user.controller;

import com.kosmo.user.dto.UserDTO;
import com.kosmo.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller

public class UserController {

    @Autowired
    private UserService userService;

    //로그인화면
    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    //로그인 했을때
    @PostMapping("/login")
    public String login(UserDTO userdto, RedirectAttributes redirectAttributes) {
        // Validate user and set session attributes if needed
        redirectAttributes.addAttribute("username", userdto.getUsername());
        return "redirect:/home"; // Redirect to home after successful login
    }

    //회원가입화면
    @GetMapping("/signup")
    public String singup() {
        return "member/signup";
    }


    //회원가입 정보보내는거
    @PostMapping("/signup")
    public String signup(UserDTO userDTO, Model model) {
        String msg;
        String loc;

        if (userDTO.getUsername() == null || userDTO.getUsername().isEmpty()) {
            msg = "회원가입에 실패했습니다. 사용자 이름이 비어 있습니다.";
            loc = "/signup";
        } else {
            int save = userService.insertUser(userDTO); // 회원가입 처리
            if (save > 0) {
                msg = "축하합니다! 코스런 회원이 되었습니다!";
                loc = "/login";
            } else {
                msg = "회원가입에 실패했습니다.";
                loc = "/signup";
            }
        }
        model.addAttribute("msg", msg);
        model.addAttribute("loc", loc);
        return "error/utility";
    }


    // 아이디 찾기 입력 페이지
    @GetMapping("/find-id")
    public String findIdPage() {
        return "member/findId"; // 아이디 찾기 입력 페이지로 이동
    }

    @PostMapping("/find-id")
    public String findUsername(@RequestParam String name, @RequestParam String email, @RequestParam String phone_number, Model model) {
        // 아이디 찾기 로직
        String username = userService.findUsername(name, email, phone_number);

        if (username != null) {
            model.addAttribute("username", username);
            return "member/findIdResult";  // 조회된 아이디를 반환하는 JSP 또는 뷰 이름
        } else {
            model.addAttribute("errorMessage", "조회 결과가 없습니다.");
            return "member/findIdResult";  // 에러 메시지를 반환하는 JSP 또는 뷰 이름
        }
    }






}