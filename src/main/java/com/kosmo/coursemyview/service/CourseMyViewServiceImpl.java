package com.kosmo.coursemyview.service;

import com.kosmo.coursemyview.dto.CourseMyViewDTO;
import com.kosmo.coursemyview.mapper.CourseMyViewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseMyViewServiceImpl implements CourseMyViewService {

    private final CourseMyViewMapper courseMyViewMapper;

    @Autowired
    public CourseMyViewServiceImpl(CourseMyViewMapper courseMyViewMapper) {
        this.courseMyViewMapper = courseMyViewMapper;
    }

    @Override
    public CourseMyViewDTO CourseMyView(Long courseId) {
        // 강의 ID에 해당하는 상세 정보 조회
        return courseMyViewMapper.CourseMyView(courseId);
    }
}
