package com.kosmo.cart.mapper;

import com.kosmo.cart.dto.MyInfoDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CartInfoMapper {

    MyInfoDTO myinfo(Long userId);
}
