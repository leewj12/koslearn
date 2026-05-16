package com.kosmo.coursemyview.controller;


import com.kosmo.coursemyview.dto.CourseMyViewDTO;
import com.kosmo.coursemyview.service.CourseMyViewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Slf4j
@Controller
public class CourseMyViewController {

    @Autowired
    private CourseMyViewService courseMyViewService;

    //내가 구매한 강의목록
    @GetMapping("/my/courses")
    public String mycourses() {
        return "/coursemyview/coursemyview";  // 뷰 이름 반환 테스트버젼
    }

    //구매한 강의 상세보기
    @GetMapping("/course/myview/{course_id}")
    public String coursemyview(@PathVariable("course_id") Long courseId, Model model) {
        log.info("테스트용======================================={}", courseId);

        // 서비스 호출하여 강의 정보를 가져옴
        CourseMyViewDTO courseMyViewDTO = courseMyViewService.CourseMyView(courseId);

        // 모델에 강의 데이터를 추가
        model.addAttribute("course", courseMyViewDTO);
        log.info("test확인용 강의데이터 =============================================={}",courseMyViewDTO);


        return "/coursemyview/courseviewpay";  // 강의 상세 페이지 (Thymeleaf 뷰)
    }


}
