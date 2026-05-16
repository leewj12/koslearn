package com.kosmo.cart.service;

import java.util.List;

public interface CartService {
    void addToCart(Long userId, String lectureName);  // 강의를 장바구니에 추가하는 메서드

    boolean removeItem(Long courseId, Long userId);   // 강의를 장바구니에서 삭제하는 메서드
}