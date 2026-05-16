package com.kosmo.admin.service;

import com.kosmo.admin.dto.MemberDTO;
import com.kosmo.admin.dto.MemberPagingDTO;
import com.kosmo.user.dto.UserDTO;

public interface MemberService {
    // 페이징 및 검색 통합
    MemberPagingDTO listMember(MemberPagingDTO pagingDTO);

    // 유저 수정
    int updateMemberById(UserDTO userDTO);

    // 유저 삭제
    int deleteMemberById(Long id);

    //유저 조회
    MemberDTO getUsers(Long id);
}