package com.kosmo.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {
 //error 경로를 처리 에러났을경우 화이트라벨 안띄우기
    @RequestMapping("/error")
    public String handleError() {
        // 커스텀 에러 페이지를 반환
        return "error/customError";  // /templates/error/customError.html을 렌더링
    }
}
