package com.kosmo.course.controller;

import com.kosmo.course.dto.CourseviewPageingDTO;
import com.kosmo.course.service.CourseViewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class CourseViewController {

    @Autowired
    private CourseViewService courseViewService;

    @GetMapping("/course/view")
    public String viewCourses(Model model,
                              @RequestParam(defaultValue = "1") int currentPage,
                              @RequestParam(defaultValue = "") String searchQuery) {
        // 총 강의 수를 먼저 구하고
        int totalCount = courseViewService.getTotalCourseCount(searchQuery);

        // 전체 페이지 수 계산
        int totalPages = (int) Math.ceil((double) totalCount / 30);

        // currentPage 값이 총 페이지 수를 초과하지 않도록 처리
        if (currentPage < 1) {
            currentPage = 1;
        } else if (currentPage > totalPages && totalPages > 0) {
            currentPage = totalPages;
        }

        // 서비스에서 검색어를 반영하여 데이터 조회 및 페이징 처리
        CourseviewPageingDTO paging = courseViewService.getCourseListWithPagination(currentPage, searchQuery);

        // 페이지 범위 계산 (1~5, 6~10 페이지 그룹 등)
        paging.calculatePageRange();

        // 모델에 페이징 객체와 관련 데이터를 추가
        model.addAttribute("paging", paging);
        model.addAttribute("courses", paging.getCourseviewList());
        model.addAttribute("searchQuery", searchQuery);  // 입력된 검색어를 모델에 추가하여 뷰에서 사용할 수 있도록 함

        return "course/courseview";  // 결과를 표시할 뷰로 이동
    }
}
