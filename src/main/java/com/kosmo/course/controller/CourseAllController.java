package com.kosmo.course.controller;


import com.kosmo.course.dto.CourseAllviewDTO;
import com.kosmo.course.service.CourseAllService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class CourseAllController {

    @Autowired
    private CourseAllService courseService;


    //강의 상세보기 부분임
    @GetMapping("/course/{title}")
    public String courstest(@PathVariable String title, Model model) {
        // 강의 제목을 이용해 강의 정보 가져오기
        CourseAllviewDTO couresallview = courseService.couresallview(title);

        // 강의 정보 확인
        log.info("확인용=================================================={}", couresallview);
        log.info("강의 이미지 확인: {}", couresallview.getImage());






        // CourseviewDTO 객체를 모델에 추가하여 템플릿에서 사용
        model.addAttribute("course", couresallview);

        // 강의 상세 페이지로 이동
        return "/course/course";
    }


}


