package com.kosmo.cart.service;

import com.kosmo.cart.dto.MyInfoDTO;
import com.kosmo.cart.mapper.CartInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartInfoServiceImpl implements CartInfoService {

    @Autowired
    private CartInfoMapper purchaseMapper;  // PurchaseMapper 주입

    @Override
    public MyInfoDTO myinfo(Long userId) {
        // PurchaseMapper를 통해 사용자 정보 조회
        return purchaseMapper.myinfo(userId);
    }
}