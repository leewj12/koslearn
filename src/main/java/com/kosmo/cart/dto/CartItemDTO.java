package com.kosmo.cart.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CartItemDTO {
    private Long cartId;
    private Long userId;
    private Long course_id;
    private String title;
    private String description;
    private String summary;
    private String image;
    private int price;
    private String instructor_Nickname;  // 강사 닉네임 필드 추가
}
