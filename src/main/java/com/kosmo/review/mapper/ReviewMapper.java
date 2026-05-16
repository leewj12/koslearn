package com.kosmo.review.mapper;

import com.kosmo.review.dto.ReviewDTO;
import com.kosmo.review.dto.ReviewInfoDTO;  // ReviewInfoDTO 임포트
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewMapper {

    //리뷰 저장하는 메서드
    int savereview(ReviewDTO reviewDTO);

    // 강의 정보를 조회하는 메서드
    ReviewInfoDTO getCourseInfo(Long courseId);

    // 이미 해당 강의에 리뷰가 존재하는지 체크
    int checkReviewExist(Long userId, Long courseId);


}



