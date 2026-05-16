package com.kosmo.instructor.service;

import com.kosmo.instructor.dto.*;

import java.util.List;

public interface InstructorService {

    void saveInstructor(InstructorDTO instructorDTO);

    GetUserDTO getUserInfo(Long userId);

    InstructorAllDTO getUserInfoAll(Long userId);

    InstructorIdDTO getInstructorId(Long userId);

    int CourseDatasave(CourseDataDTO courseDataDTO);

    InstructorDTO getInstructorByUserId(long userId);

    void updateInstructor(InstructorDTO instructorDTO);

    InstructorReviewDTO instructorReview(Long userId);

    List<CourseDataDTO> courselist(Long userId);

}
