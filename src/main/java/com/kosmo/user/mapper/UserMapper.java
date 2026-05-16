package com.kosmo.user.mapper;

import com.kosmo.user.dto.UserAllDTO;
import com.kosmo.user.dto.UserDTO;
import com.kosmo.user.dto.UsersImageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    UserDTO findUserByUsername(String user);

    int insertUser(UserDTO userDTO);

    int countByUsername(String username);

    //추가 로직
    void saveUserImage(UsersImageDTO usersImageDTO);
    void updateUser(UserDTO userDTO);
    UserDTO findUserByUserid(long userId);
    void updateUserImage(UsersImageDTO usersImageDTO);
    void saverorupdateImage(UsersImageDTO usersImageDTO);
    UsersImageDTO findUserImageById(long userId);
    UserAllDTO findUserAllById(long userId);

    String findUsernameByDetails(@Param("name") String name, @Param("email") String email, @Param("phone_number") String phone_number);



}
