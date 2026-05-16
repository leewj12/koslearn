package com.kosmo.instructor.mapper;

import com.kosmo.instructor.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InstructorMapper {

    // 강사 프로필 등록
    void insertInstructor(InstructorDTO instructorDTO);

    GetUserDTO getUserInfo(Long userId);

    InstructorAllDTO getUserInfoAll(Long userId);

    InstructorIdDTO getInstructorId(Long userId);  // MyBatis 매퍼 메서드

    int CourseDatasave(CourseDataDTO courseDataDTO);
    InstructorDTO getInstructorByUserId(long userId);
    void updateInstructor(InstructorDTO instructorDTO);

    int getCourseCountByUserId(Long userId);   // 강의 개수 조회
    Double getInstructorRatingByUserId(Long userId);

    List<CourseDataDTO> courselist(Long userId);


}