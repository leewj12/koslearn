package com.kosmo.user.service;

import com.kosmo.user.dto.UserAllDTO;
import com.kosmo.user.dto.UserDTO;
import com.kosmo.user.dto.UsersImageDTO;
import com.kosmo.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;  // BCryptPasswordEncoder 의존성 주입

    @Override
    public int insertUser(UserDTO userDTO) {
        // 비밀번호를 해싱하여 저장
        String hashedPassword = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(hashedPassword);  // 해싱된 비밀번호로 설정

        return userMapper.insertUser(userDTO);  // 비밀번호가 해싱된 상태로 데이터베이스에 저장
    }


    //중복아이디 찾는 기능임
    @Override
    public boolean isUsernameExists(String username) {
        try {
            return userMapper.countByUsername(username) > 0;
        } catch (Exception e) {
            // 예외 로깅 및 디버깅
            System.err.println("Error checking username: " + e.getMessage());
            return false;
        }
    }

    //추가로직
    @Override
    public UserDTO findUserByUsername(String user) {
        return findUserByUsername(user);
    }

    @Override
    public UserDTO findUserByUserid(long userId) {
        return userMapper.findUserByUserid(userId);
    }

    @Override
    public void saveUserImage(UsersImageDTO usersImageDTO) {
        userMapper.saveUserImage(usersImageDTO);
    }

    @Override
    public void updateUserImage(UsersImageDTO usersImageDTO) {
        userMapper.updateUserImage(usersImageDTO);
    }

    @Override
    public void saverorupdateImage(UsersImageDTO usersImageDTO) {
        UsersImageDTO userImage=userMapper.findUserImageById(usersImageDTO.getUserId());
        if(userImage!=null){
            userMapper.updateUserImage(usersImageDTO);
        }
        else{
            userMapper.saveUserImage(usersImageDTO);
        }
    }

    @Override
    public UsersImageDTO findUserImageById(long userId) {
        return userMapper.findUserImageById(userId);
    }

    @Override
    public UserAllDTO findUserAllById(long userId) {
        return userMapper.findUserAllById(userId);
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        userMapper.updateUser(userDTO);
    }


    @Override
    public String findUsername(String name, String email, String phone_number) {
        // UserMapper를 사용하여 DB에서 아이디를 조회
        return userMapper.findUsernameByDetails(name, email, phone_number);
    }



}



