package com.kosmo.purchase.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PayMentDTO {

    private Long userId;           // 사용자 ID
    private List<Long> courseIds;  // 강의 ID 리스트
    private List<Long> cartIds;    // 장바구니 ID 리스트
    private int totalAmount;    // 총 결제 금액
    private String cardNumber;     // 카드 번호
    private String expiryDate;     // 카드 유효 기간 (MM/YY)
    private String cvc;            // 카드 CVC 코드

}
