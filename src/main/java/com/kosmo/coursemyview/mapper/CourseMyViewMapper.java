package com.kosmo.coursemyview.mapper;

import com.kosmo.coursemyview.dto.CourseMyViewDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseMyViewMapper {
 CourseMyViewDTO CourseMyView(Long courseId);

}

