package com.kosmo.admin.service;

import com.kosmo.admin.dto.AdminCourselistDTO;

import java.util.List;

public interface AdminCourselistService {
    List<AdminCourselistDTO> Courselist();

    void  deleteCourse(Long courseId);

    AdminCourselistDTO Coursedetail(long courseId);
}
