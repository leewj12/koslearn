package com.kosmo.community.mapper;

import com.kosmo.community.dto.PagingDTO;
import com.kosmo.community.dto.CommunityDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PagingMapper {

    // 페이징 처리된 커뮤니티 게시글 목록을 반환하는 메서드
    List<CommunityDTO> listCommunity(PagingDTO pagingDTO);

    // 전체 데이터 수를 반환하는 메서드
    int getTotalCount(PagingDTO pagingDTO);
}
