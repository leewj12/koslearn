package com.kosmo.admin.mapper;

import com.kosmo.admin.dto.MemberDTO;
import com.kosmo.admin.dto.MemberPagingDTO;
import com.kosmo.user.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    // 전체 사용자 수 조회
    int getTotalCount(MemberPagingDTO pagingDTO);

    // 페이징 처리된 사용자 목록 조회
    List<MemberDTO> listMember(MemberPagingDTO pagingDTO);

    //유저 수정
    int updateMemberById(UserDTO userDTO);

    //유저 삭제
    int deleteMemberById(Long id);

    //유저 조회
    MemberDTO getMemberById(Long id);
}
