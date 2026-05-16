package com.kosmo.instructor.controller;

import com.kosmo.instructor.dto.*;
import com.kosmo.instructor.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructor")
public class InstructorRest {

    private final InstructorService instructorService;

    @Autowired
    public InstructorRest(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    // 사용자 정보 가져오기
    @GetMapping("/getUserInfo")
    public ResponseEntity<GetUserDTO> getUserInfo(@RequestParam Long userId) {
        GetUserDTO getUserDTO = instructorService.getUserInfo(userId);
        return ResponseEntity.ok(getUserDTO);
    }


    // 강사 전체 정보 가져오기
    @GetMapping("/getUserInfoAll")
    public ResponseEntity<InstructorAllDTO> getUserInfoAll(@RequestParam Long userId) {
        InstructorAllDTO instructorAllDTO = instructorService.getUserInfoAll(userId);
        return ResponseEntity.ok(instructorAllDTO);
    }

    // 유저 ID를 받아서 강사 ID 반환
    @GetMapping("/getInstructorId")
    public ResponseEntity<InstructorIdDTO> getUserInfoId(@RequestParam Long userId) {
        InstructorIdDTO instructorIdDTO = instructorService.getInstructorId(userId);
        return ResponseEntity.ok(instructorIdDTO);
    }


    //강사 강의 수 및 강사 평균평점 가져오기
    @GetMapping("/review")
    public ResponseEntity<InstructorReviewDTO> inreview(@RequestParam Long userId) {
        InstructorReviewDTO instructorReviewDTO = instructorService.instructorReview(userId);
        return ResponseEntity.ok(instructorReviewDTO);
    }

    //강의 목록 불러오기 및 수정하기 //삭제기능은 어드민만
    @GetMapping("/courselist")
    public ResponseEntity<List<CourseDataDTO>> courselist(@RequestParam Long userId) {
        List<CourseDataDTO> courselist = instructorService.courselist(userId);
        return ResponseEntity.ok(courselist);

    }

}