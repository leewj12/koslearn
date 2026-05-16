package com.kosmo.review.controller;

import com.kosmo.review.dto.ReviewDTO;
import com.kosmo.review.dto.ReviewInfoDTO;
import com.kosmo.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // course_id를 경로 변수로 받아서 사용
    @GetMapping("/course/my/review/{course_id}")
    public String courseReview(@PathVariable("course_id") Long courseId, Model model) {
        // courseId를 활용하여 강의 정보 조회

        ReviewInfoDTO courseInfo = reviewService.getCourseInfo(courseId);

        // 모델에 강의 정보를 추가하여 Thymeleaf에서 사용할 수 있도록 전달
        model.addAttribute("courseInfo", courseInfo);

        // 리뷰 페이지로 이동 (review/coursereview.html)
        return "review/coursereview";
    }




    @PostMapping("/course/my/review")
    public String submitReview(ReviewDTO reviewDTO, Model model) {
        String msg;
        String loc;




        // 리뷰가 이미 작성되었는지 확인
        boolean isExist = reviewService.isReviewExist(reviewDTO.getUserId(), reviewDTO.getCourseId());

        if (isExist) {
            // 이미 리뷰가 작성된 경우
            msg = "이미 리뷰가 작성이 되었습니다. 수정 부탁드립니다";
            loc = "/my/courses";

            model.addAttribute("msg", msg);
            model.addAttribute("loc", loc);


            //고쳐야함 로그인으로 가기에 loc
            return "/error/utility"; // 리뷰 작성 불가 시 오류 페이지로 리다이렉트
        }

        // 리뷰 저장
        int result = reviewService.savereview(reviewDTO);

        msg = "리뷰가 작성이 되었습니다. 감사합니다";
        loc = "/my/courses";

        model.addAttribute("msg", msg);
        model.addAttribute("loc", loc);


        // 리뷰 작성 후 결과 페이지로 이동
        if (result > 0) {
            return "/error/utility"; // 저장 성공시
        } else {
            return "redirect:/course/error"; // 실패시 오류 페이지로 리다이렉트
        }
    }
}
