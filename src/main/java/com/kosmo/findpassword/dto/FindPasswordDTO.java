package com.kosmo.findpassword.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class FindPasswordDTO {
    private String username; // 사용자 아이디
    private String name;     // 사용자 이름
    private String email;    // 사용자 이메일

}
