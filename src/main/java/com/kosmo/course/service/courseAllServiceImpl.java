package com.kosmo.course.service;

import com.kosmo.course.dto.CourseAllviewDTO;
import com.kosmo.course.mapper.CourseAllMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class courseAllServiceImpl implements CourseAllService {

    @Autowired
    private CourseAllMapper courseallMapper;

    @Override
    public CourseAllviewDTO couresallview(String title) {
        return courseallMapper.couresallview(title); // mapper에서 쿼리 실행
    }
}