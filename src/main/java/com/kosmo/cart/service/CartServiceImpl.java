package com.kosmo.cart.service;

import com.kosmo.cart.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    // 강의 추가
    public void addToCart(Long userId, String lectureName) {
        // lectureName으로 courseId 찾기
        Long courseId = cartMapper.findCourseIdByName(lectureName);

        if (courseId != null) {
            // 이미 장바구니에 있는지 확인
            if (isCourseInCart(userId, courseId)) {
                throw new IllegalArgumentException("이미 장바구니에 추가된 강의입니다.");
            }

            // 장바구니에 추가
            Map<String, Object> params = new HashMap<>();
            params.put("userId", userId);
            params.put("courseId", courseId);

            cartMapper.addToCart(params);
        } else {
            throw new IllegalArgumentException("강의를 찾을 수 없습니다.");
        }
    }

    @Override
    public boolean removeItem(Long courseId, Long userId) {
        // 삭제가 성공적으로 이루어졌다면 1을 반환, 그렇지 않으면 0을 반환
        return cartMapper.removeItem(courseId, userId) > 0;
    }

    // 장바구니에 이미 강의가 있는지 확인하는 메서드
    private boolean isCourseInCart(Long userId, Long courseId) {
        Integer count = cartMapper.checkIfCourseInCart(userId, courseId);
        return count != null && count > 0;
    }
}
