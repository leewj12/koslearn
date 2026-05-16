package com.kosmo.community.service;

import com.kosmo.community.dto.PagingDTO;

public interface PagingService {
    PagingDTO getCommunityList(PagingDTO pagingDTO);  // 페이징 처리된 커뮤니티 게시글 목록 가져오기
}
