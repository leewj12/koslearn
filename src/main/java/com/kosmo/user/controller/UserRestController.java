package com.kosmo.user.controller;

import com.kosmo.user.dto.UserDTO;
import com.kosmo.user.dto.UsersImageDTO;
import com.kosmo.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@Slf4j
@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    // 중복체크확인 나중에 추후에 다시 해야함 / 막힘
    @GetMapping("/check-username")
    public Map<String, Boolean> checkUsername(@RequestParam String username) {
        boolean exists = userService.isUsernameExists(username);
        return Collections.singletonMap("exists", exists); // 아이디 중복 여부를 JSON 형식으로 반환
    }

     //유저 데이터 보여주는 ajax
    @GetMapping("/my/getMyInfo")
    public ResponseEntity<UserDTO> getMyInfo(@RequestParam Long userId){
        UserDTO userDTO=userService.findUserByUserid(userId);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("my/getMyImage")
    public ResponseEntity<UsersImageDTO> getMyImage(@RequestParam Long userId){
        UsersImageDTO usersImageDTO=userService.findUserImageById(userId);
        return ResponseEntity.ok(usersImageDTO);
    }




}
