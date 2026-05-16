package com.kosmo.cart.controller;

import com.kosmo.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addToCart(@RequestBody Map<String, String> request) {
        String userId = request.get("userId");
        String lectureName = request.get("lectureName");

        try {
            // 강의 이름으로 강좌를 추가
            cartService.addToCart(Long.parseLong(userId), lectureName);

            // 성공 응답을 JSON 형식으로 반환
            Map<String, String> response = Map.of("message", "강의가 장바구니에 추가되었습니다.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            // 이미 장바구니에 있는 경우
            Map<String, String> response = Map.of("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // 실패 응답을 JSON 형식으로 반환
            Map<String, String> response = Map.of("message", "오류가 발생했습니다: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
