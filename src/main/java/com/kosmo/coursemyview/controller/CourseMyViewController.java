package com.kosmo.coursemyview.controller;


import com.kosmo.coursemyview.dto.CourseMyViewDTO;
import com.kosmo.coursemyview.service.CourseMyViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class CourseMyViewController {

    @Autowired
    private CourseMyViewService courseMyViewService;

    //내가 구매한 강의목록
    @GetMapping("/my/courses")
    public String mycourses() {
        return "coursemyview/coursemyview";  // 뷰 이름 반환 테스트버젼
    }

    //구매한 강의 상세보기
    @GetMapping("/course/myview/{course_id}")
    public String coursemyview(@PathVariable("course_id") Long courseId, Model model) {
        CourseMyViewDTO courseMyViewDTO = courseMyViewService.CourseMyView(courseId);
        model.addAttribute("course", courseMyViewDTO);
        return "coursemyview/courseviewpay";  // 강의 상세 페이지 (Thymeleaf 뷰)
    }


}
