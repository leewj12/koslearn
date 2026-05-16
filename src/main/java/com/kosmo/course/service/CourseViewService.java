package com.kosmo.course.service;

import com.kosmo.course.dto.CourseviewPageingDTO;

public interface CourseViewService {

    // 페이징 처리된 강의 목록 조회
    CourseviewPageingDTO getCourseListWithPagination(int currentPage, String searchQuery);

    // 총 강의 수 조회
    int getTotalCourseCount(String searchQuery);
}
