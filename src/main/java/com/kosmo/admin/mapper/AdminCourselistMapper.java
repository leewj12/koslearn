package com.kosmo.admin.mapper;

import com.kosmo.admin.dto.AdminCourselistDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminCourselistMapper {
    List<AdminCourselistDTO> Courselist();


    void  deleteCourse(Long courseId);

    AdminCourselistDTO Coursedetail(long courseId);
}
