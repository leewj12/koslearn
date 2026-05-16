package com.kosmo.course.service.impl;

import com.kosmo.course.dto.CourseviewDTO;
import com.kosmo.course.dto.CourseviewPageingDTO;
import com.kosmo.course.mapper.CourseviewMapper;
import com.kosmo.course.service.CourseViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseViewServiceImpl implements CourseViewService {

    @Autowired
    private CourseviewMapper courseviewMapper;

    @Override
    public CourseviewPageingDTO getCourseListWithPagination(int currentPage, String searchQuery) {
        // 페이징 DTO 생성
        CourseviewPageingDTO paging = new CourseviewPageingDTO(currentPage);

        // 전체 강의 수 구하기
        int totalCount = getTotalCourseCount(searchQuery);

        // 전체 페이지 수 계산
        paging.setTotalCount(totalCount);

        // 강의 목록 조회
        List<CourseviewDTO> courseviewList = courseviewMapper.getCourseListWithPagination(paging.getStart(), paging.getEnd(), searchQuery);
        paging.setCourseviewList(courseviewList);

        // 페이지 그룹 계산
        paging.calculatePageRange();

        return paging;
    }

    @Override
    public int getTotalCourseCount(String searchQuery) {
        return courseviewMapper.getTotalCourseCount(searchQuery);
    }
}
