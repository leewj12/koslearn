package com.kosmo.mypage.controller;

import com.kosmo.mypage.dto.PayDTO;
import com.kosmo.mypage.service.MyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MyPageController {

    @Autowired
    private MyPageService myPageService;


    //마이페이지 홈
    @GetMapping("/my/mypage")
    public String mypage(){
        return "/mypage/mypage";
    }

    //마이페이지 수정
    @GetMapping("/my/profile-update")
    public String profileUpdate(){
        return "/mypage/profile-update";
    }


}
