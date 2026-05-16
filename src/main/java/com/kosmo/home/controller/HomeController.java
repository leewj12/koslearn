package com.kosmo.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    //메인홈
    @GetMapping({"/", "/home"})
    public String gohome() {
        return "home/home";
    }
}
