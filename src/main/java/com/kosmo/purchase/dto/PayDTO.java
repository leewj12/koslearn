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
public class PayDTO {
    private Long cartId;
    private Long userId;           // 사용자 ID
    private List<Long> courseIds;  // 강의 ID 리스트
    private List<Integer> amounts; // 강의별 결제 금액 리스트
}
