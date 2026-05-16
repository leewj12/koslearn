package com.kosmo.admin.service;

import com.kosmo.admin.dto.AdminCourselistDTO;
import com.kosmo.admin.mapper.AdminCourselistMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminCourselistServiceImlp implements AdminCourselistService{

    @Autowired
    private AdminCourselistMapper adminCourselistMapper;

    @Override
    public List<AdminCourselistDTO> Courselist() {
        return adminCourselistMapper.Courselist();  // 모든 강의를 조회
    }

    @Override
    public void deleteCourse(Long courseId) {
        adminCourselistMapper.deleteCourse(courseId);
    }

    @Override
    public AdminCourselistDTO Coursedetail(long courseId) {
        return adminCourselistMapper.Coursedetail(courseId);
    }
}
