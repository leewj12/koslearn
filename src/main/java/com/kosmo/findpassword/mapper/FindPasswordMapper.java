package com.kosmo.findpassword.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FindPasswordMapper {
    Integer checkUserExistence(String username, String name, String email);

    int updatePassword(String username, String newPassword);
}
