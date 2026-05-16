package com.kosmo.mypage.service;

import com.kosmo.mypage.dto.PayDTO;
import com.kosmo.mypage.mapper.MyPageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyPageServiceImpl implements MyPageService {

    @Autowired
    private MyPageMapper myPageMapper;  // MyPageMapper 주입

    @Override
    public List<PayDTO> paycourses(int userId) {
        return myPageMapper.paycourses(userId);  // 사용자 ID를 기준으로 결제된 강의 정보 조회
    }
}
