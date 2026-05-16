package com.kosmo.mypage.service;

import com.kosmo.mypage.dto.PayDTO;
import java.util.List;

public interface MyPageService {
    // 사용자 ID를 받아 결제된 강의 목록을 반환하는 메서드
    List<PayDTO> paycourses(int userId);
}
