package com.kosmo.user.service;

import com.kosmo.user.dto.UserAllDTO;
import com.kosmo.user.dto.UserDTO;
import com.kosmo.user.dto.UsersImageDTO;

public interface UserService {
    int insertUser(UserDTO userDTO);

    boolean isUsernameExists(String username);


    //추가로직
    void updateUser(UserDTO userDTO);
    UserDTO findUserByUsername(String user);
    UserDTO findUserByUserid(long userId);
    void saveUserImage(UsersImageDTO usersImageDTO);
    void updateUserImage(UsersImageDTO usersImageDTO);
    void saverorupdateImage(UsersImageDTO usersImageDTO);
    UsersImageDTO findUserImageById(long userId);
    UserAllDTO findUserAllById(long userId);

    String findUsername(String name, String email, String phone_number);


}
