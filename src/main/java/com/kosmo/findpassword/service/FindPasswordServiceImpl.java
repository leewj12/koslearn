package com.kosmo.findpassword.service;

import com.kosmo.findpassword.dto.FindPasswordDTO;
import com.kosmo.findpassword.mapper.FindPasswordMapper;
import com.kosmo.findpassword.service.FindPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class FindPasswordServiceImpl implements FindPasswordService {

    @Autowired
    private FindPasswordMapper findPasswordMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;  // BCryptPasswordEncoder 주입

    @Override
    public boolean checkUserInformation(FindPasswordDTO findPasswordDTO) {
        // 사용자 정보 확인 - 비밀번호는 확인하지 않음
        Integer userCount = findPasswordMapper.checkUserExistence(findPasswordDTO.getUsername(), findPasswordDTO.getName(), findPasswordDTO.getEmail());

        // 사용자 정보가 존재하면 1을 반환 (사용자가 존재하면 true)
        return userCount != null && userCount > 0;
    }

    @Override
    public boolean changePassword(String newPassword, String username) {
        String encodedPassword = passwordEncoder.encode(newPassword);  // 비밀번호 암호화
        // 비밀번호 변경을 위한 쿼리 실행
        int rowsAffected = findPasswordMapper.updatePassword(username, encodedPassword);  // 사용자 비밀번호 업데이트
        return rowsAffected > 0;  // 비밀번호 업데이트 성공 여부 반환
    }


}


