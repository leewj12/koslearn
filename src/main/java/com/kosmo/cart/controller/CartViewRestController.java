package com.kosmo.cart.controller;

import com.kosmo.cart.dto.CartItemDTO;
import com.kosmo.cart.service.CartService;
import com.kosmo.cart.service.CartViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartViewRestController {

    @Autowired
    private CartViewService cartViewService;

    @Autowired
    private CartService cartService;


    //카트에 있는거 내용 보여주는거임
    @GetMapping("/cart/items/{userId}")
    public List<CartItemDTO> getCartItems(@PathVariable Long userId) {
        return cartViewService.getCartItemsByUserId(userId);
    }

    //카트에 있는거 삭제하는 거임
    @DeleteMapping("/cart/remove/{courseId}/{userId}")
    public ResponseEntity<String> removeItemFromCart(@PathVariable Long courseId, @PathVariable Long userId) {
        try {
            boolean isRemoved = cartService.removeItem(courseId, userId);  // userId와 courseId를 함께 전달
            if (isRemoved) {
                return ResponseEntity.ok("항목이 삭제되었습니다.");
            } else {
                return ResponseEntity.status(404).body("장바구니에서 해당 강의를 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("서버 오류가 발생했습니다.");
        }
    }


}