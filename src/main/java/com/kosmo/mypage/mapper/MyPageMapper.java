package com.kosmo.mypage.mapper;

import com.kosmo.mypage.dto.PayDTO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface MyPageMapper {
    // 결제된 강의 목록을 반환하는 메서드
    List<PayDTO> paycourses(int userId);
}
