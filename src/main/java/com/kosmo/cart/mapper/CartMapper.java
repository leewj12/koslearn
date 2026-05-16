package com.kosmo.cart.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CartMapper {

    // 강의 이름으로 courseId 찾기
    Long findCourseIdByName(String lectureName);

    // 장바구니에 추가
    void addToCart(Map<String, Object> params);

    // 장바구니에 해당 강의가 이미 있는지 확인
    Integer checkIfCourseInCart(Long userId, Long courseId);

    // 장바구니에서 해당 강의 삭제
    int removeItem(Long courseId, Long userId);  // 삭제 성공 시 1, 실패 시 0 반환

}
