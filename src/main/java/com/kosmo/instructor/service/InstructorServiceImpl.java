package com.kosmo.instructor.service;

import com.kosmo.instructor.dto.*;
import com.kosmo.instructor.mapper.InstructorMapper;
import com.kosmo.instructor.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService {

    @Autowired
    private InstructorMapper instructorMapper;

    @Override
    public void saveInstructor(InstructorDTO instructorDTO) {
        // 매퍼를 통해 DB에 데이터 저장
        InstructorDTO existingInstructor = instructorMapper.getInstructorByUserId(instructorDTO.getUserId());

        if (existingInstructor != null) {
            // 이미 존재하면 update
            instructorMapper.updateInstructor(instructorDTO);
        } else {
            // 존재하지 않으면 insert
            instructorMapper.insertInstructor(instructorDTO);
        }
    }

    @Override
    public GetUserDTO getUserInfo(Long userId) {
        return instructorMapper.getUserInfo(userId);
    }

    @Override
    public InstructorAllDTO getUserInfoAll(Long userId) {
        return instructorMapper.getUserInfoAll(userId);
    }

    @Override
    public InstructorIdDTO getInstructorId(Long userId) {
        return instructorMapper.getInstructorId(userId);  // MyBatis 매퍼 호출
    }

    @Override
    public int CourseDatasave(CourseDataDTO courseDataDTO) {
        return instructorMapper.CourseDatasave(courseDataDTO);
    }

    @Override
    public InstructorDTO getInstructorByUserId(long userId) {
        return instructorMapper.getInstructorByUserId(userId);
    }

    @Override
    public void updateInstructor(InstructorDTO instructorDTO) {

    }

    @Override
    public InstructorReviewDTO instructorReview(Long userId) {
        int courseCount = instructorMapper.getCourseCountByUserId(userId);  // 강의 개수 조회



        Double instructorRating = instructorMapper.getInstructorRatingByUserId(userId);


        InstructorReviewDTO dto = new InstructorReviewDTO();
        dto.setCourseCount(courseCount);  // 강의 개수 설정
        dto.setInstructorRating(instructorRating);  // 강사 평점 평균 설정
        return dto;
    }

    @Override
    public List<CourseDataDTO> courselist(Long userId) {
        return instructorMapper.courselist(userId);
    }
}
