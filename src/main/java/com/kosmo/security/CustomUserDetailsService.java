package com.kosmo.security;

import com.kosmo.user.dto.UserDTO;
import com.kosmo.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;  // MyBatis Mapper 사용

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userDTO = userMapper.findUserByUsername(username);  // MyBatis Mapper에서 사용자를 찾음

        if (userDTO == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }

        // CustomUserDetails 객체를 직접 반환
        return new CustomUserDetails(
                userDTO.getId(),               // ID 값
                userDTO.getUsername(),         // 사용자 이름
                userDTO.getPassword(),         // 비밀번호 (암호화된 상태여야 함)
                userDTO.getRole()              // 역할
        );
    }
}
