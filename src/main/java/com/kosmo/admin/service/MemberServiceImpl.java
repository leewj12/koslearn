package com.kosmo.admin.service;

import com.kosmo.admin.dto.MemberDTO;
import com.kosmo.admin.dto.MemberPagingDTO;
import com.kosmo.admin.mapper.MemberMapper;
import com.kosmo.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public MemberPagingDTO listMember(MemberPagingDTO pagingDTO) {
        // 검색 조건 및 전체 데이터 수 조회
        int totalCount = memberMapper.getTotalCount(pagingDTO);
        pagingDTO.setTotalCount(totalCount);

        // 페이징된 사용자 목록 조회 (pageSize 반영)
        pagingDTO.setMembers(memberMapper.listMember(pagingDTO));

        return pagingDTO;
    }

    @Override
    public int updateMemberById(UserDTO userDTO) {
        return memberMapper.updateMemberById(userDTO);
    }

    @Override
    public int deleteMemberById(Long id) {
        return memberMapper.deleteMemberById(id);
    }

    //유저 조회
    @Override
    public MemberDTO getUsers(Long id) {
        return memberMapper.getMemberById(id);
    }

}
