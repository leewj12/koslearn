package com.kosmo.review.service;

import com.kosmo.review.dto.ReviewDTO;
import com.kosmo.review.dto.ReviewInfoDTO;  // ReviewInfoDTO 임포트

public interface ReviewService {

    //리뷰를 저장함
    int savereview(ReviewDTO reviewDTO);

    // 강의 정보를 가져오는 메서드
    ReviewInfoDTO getCourseInfo(Long courseId);

    // 이미 리뷰가 작성되었는지 확인하는 메서드
    boolean isReviewExist(Long userId, Long courseId);

}


