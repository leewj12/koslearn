package com.kosmo.purchase.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PurchaseInfoDTO {
    private int cartId;
    private int userId;
    private int courseId;
    private String name;
    private String email;
    private String phone_number; // 전화번호는 String으로 수정
    private String title;
    private int price;  // 가격을 int로 변경
    private String nickname; // 강사 닉네임
}
