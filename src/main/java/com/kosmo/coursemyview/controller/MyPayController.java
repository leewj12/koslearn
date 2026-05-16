package com.kosmo.coursemyview.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyPayController {

    @GetMapping("/mypay/pay")
    public String mypaypay(){
        return "/mypage/mypay";
    }
}
