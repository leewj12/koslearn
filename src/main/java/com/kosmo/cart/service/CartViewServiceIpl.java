package com.kosmo.cart.service;

import com.kosmo.cart.dto.CartItemDTO;
import com.kosmo.cart.mapper.CartViewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartViewServiceIpl implements CartViewService {

    @Autowired
    private CartViewMapper cartViewMapper;

    @Override
    public List<CartItemDTO> getCartItemsByUserId(long userId) {
        return cartViewMapper.getCartItemsByUserId(userId);
    }
}
