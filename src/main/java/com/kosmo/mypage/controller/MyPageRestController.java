package com.kosmo.mypage.controller;

import com.kosmo.mypage.dto.PayDTO;
import com.kosmo.mypage.service.MyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MyPageRestController {

    @Autowired
    private MyPageService myPageService;  // MyPageService를 주입

    // 사용자 ID를 받아 결제된 강의 목록을 반환하는 GET 메서드
    @GetMapping("/my/paycourses")
    public List<PayDTO> getMyCourses(@RequestParam int userId) {
        // userId를 기반으로 결제된 강의 목록 조회
        return myPageService.paycourses(userId);
    }
}
