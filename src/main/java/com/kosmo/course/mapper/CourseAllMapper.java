package com.kosmo.course.mapper;

import com.kosmo.course.dto.CourseAllviewDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseAllMapper {
    CourseAllviewDTO couresallview(String title);  // 오타 수정
}
