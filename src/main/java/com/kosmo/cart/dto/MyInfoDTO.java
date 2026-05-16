package com.kosmo.cart.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MyInfoDTO {

    private String name;            // 이름
    private String email;           // 이메일
    private String phone_number;    // 연락처
}
