package com.kosmo.findpassword.service;

import com.kosmo.findpassword.dto.FindPasswordDTO;

public interface FindPasswordService {
    boolean checkUserInformation(FindPasswordDTO findPasswordDTO);

    boolean changePassword(String newPassword, String username);  // 비밀번호 변경 메소드 추가
}

