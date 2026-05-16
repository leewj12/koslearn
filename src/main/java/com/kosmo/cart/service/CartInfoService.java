package com.kosmo.cart.service;

import com.kosmo.cart.dto.MyInfoDTO;

public interface CartInfoService {
    MyInfoDTO myinfo(Long userId);  // 사용자 정보 가져오는 메서드
}
