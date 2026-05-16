package com.kosmo.cart.mapper;

import com.kosmo.cart.dto.CartItemDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartViewMapper {
    List<CartItemDTO>  getCartItemsByUserId(long userId);
}

