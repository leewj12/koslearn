package com.kosmo.cart.service;

import com.kosmo.cart.dto.CartItemDTO;
import com.kosmo.cart.mapper.CartViewMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class CartViewServiceIpl implements CartViewService {

    @Autowired
    private CartViewMapper cartViewMapper;

    @Override
    public List<CartItemDTO> getCartItemsByUserId(long userId) {
        // Mapper를 사용해 특정 사용자 ID에 해당하는 장바구니 항목을 가져옵니다.
        List<CartItemDTO>  test123 = cartViewMapper.getCartItemsByUserId(userId);

        log.info("테스트확인용================================================================{}",test123);
        return cartViewMapper.getCartItemsByUserId(userId);
    }
}
