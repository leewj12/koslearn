package com.kosmo.cart.service;

import com.kosmo.cart.dto.CartItemDTO;

import java.util.List;

public interface CartViewService {
    List<CartItemDTO> getCartItemsByUserId(long userId);
}
