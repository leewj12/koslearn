package com.kosmo.instructor.controller;

import com.kosmo.instructor.dto.*;
import com.kosmo.instructor.service.InstructorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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
        System.out.println("Requested User ID: " + userId);
        GetUserDTO getUserDTO = instructorService.getUserInfo(userId);
        log.info("-------------------------------------------------------==={}", getUserDTO);
        return ResponseEntity.ok(getUserDTO);
    }


    // 강사 전체 정보 가져오기
    @GetMapping("/getUserInfoAll")
    public ResponseEntity<InstructorAllDTO> getUserInfoAll(@RequestParam Long userId) {
        InstructorAllDTO instructorAllDTO = instructorService.getUserInfoAll(userId);
        log.info("-----------------------------------------------------=디비디비={}", instructorAllDTO);
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
        log.info("아이디확인=========================================================={}", userId);
        InstructorReviewDTO instructorReviewDTO = instructorService.instructorReview(userId);
        log.info("강의랑 평점 확인용========================================{}", instructorReviewDTO);

        return ResponseEntity.ok(instructorReviewDTO);
    }

    //강의 목록 불러오기 및 수정하기 //삭제기능은 어드민만
    @GetMapping("/courselist")
    public ResponseEntity<List<CourseDataDTO>> courselist(@RequestParam Long userId) {
        List<CourseDataDTO> courselist = instructorService.courselist(userId);
        log.info("강의데이터 확인용================================================={}",courselist );

        return ResponseEntity.ok(courselist);

    }

}