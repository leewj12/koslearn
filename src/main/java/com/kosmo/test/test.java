package com.kosmo.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class test {
    @GetMapping("/test")
    public String testPage() {
        return "test/test";  // test/test.html을 렌더링하려고 시도
    }


@GetMapping("/test/test")
public String testao() {
    return "mypage/mypqgetest";  // test/test.html을 렌더링하려고 시도
}
}