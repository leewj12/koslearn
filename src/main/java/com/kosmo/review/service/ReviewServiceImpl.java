package com.kosmo.review.service;

import com.kosmo.review.dto.ReviewDTO;
import com.kosmo.review.dto.ReviewInfoDTO;  // ReviewInfoDTO 임포트
import com.kosmo.review.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    //리뷰를 저장
    @Override
    public int savereview(ReviewDTO reviewDTO) {
        return reviewMapper.savereview(reviewDTO);
    }

    @Override
    public ReviewInfoDTO getCourseInfo(Long courseId) {
        // ReviewMapper에서 강의 정보를 가져옴
        return reviewMapper.getCourseInfo(courseId);
    }

    // 해당 강의에 리뷰가 존재하는지 확인
    @Override
    public boolean isReviewExist(Long userId, Long courseId) {
        return reviewMapper.checkReviewExist(userId, courseId) > 0;
    }


}
