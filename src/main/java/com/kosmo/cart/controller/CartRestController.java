package com.kosmo.cart.controller;

import com.kosmo.cart.dto.MyInfoDTO;
import com.kosmo.cart.service.CartInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartRestController {

    @Autowired
    private CartInfoService cartInfoService;

    // 장바구니에서 사용자 정보 보여주기
    @GetMapping("/getUserInfo/{userId}")
    public ResponseEntity<MyInfoDTO> getUserInfo(@PathVariable("userId") Long userId) {
        // 서비스에서 사용자 정보 가져오기
        MyInfoDTO userinfo = cartInfoService.myinfo(userId);

        // 사용자 정보가 있으면 반환, 없으면 404 반환
        if (userinfo != null) {
            return ResponseEntity.ok(userinfo);  // JSON 형식으로 반환
        } else {
            return ResponseEntity.status(404).body(null);  // 404 오류
        }
    }
}
