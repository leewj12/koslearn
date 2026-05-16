package com.kosmo.community.service;

import com.kosmo.community.dto.PagingDTO;
import com.kosmo.community.dto.CommunityDTO;
import com.kosmo.community.mapper.PagingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagingServiceImpl implements PagingService {

    @Autowired
    private PagingMapper pagingMapper;

    @Override
    public PagingDTO getCommunityList(PagingDTO pagingDTO) {
        // 전체 데이터 수를 가져와서 PagingDTO에 설정
        int totalCount = pagingMapper.getTotalCount(pagingDTO);  // 전체 데이터 수를 가져오기
        pagingDTO.setTotalCount(totalCount);  // 전체 페이지 수 계산

        // 페이징 처리된 커뮤니티 게시글 목록 가져오기
        List<CommunityDTO> communityList = pagingMapper.listCommunity(pagingDTO);
        pagingDTO.setCommunityList(communityList);  // 커뮤니티 목록 설정

        return pagingDTO;  // 페이징 처리된 결과 반환
    }
}
