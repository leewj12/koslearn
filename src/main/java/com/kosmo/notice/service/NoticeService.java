package com.kosmo.notice.service;

import com.kosmo.notice.dto.NoticeDTO;
import com.kosmo.notice.dto.NoticePagingDTO;

public interface NoticeService {

    // 공지사항 저장
    int saveNotice(NoticeDTO noticeDTO);

    // 페이징된 공지사항 목록을 가져오는 메서드 (검색어 및 검색 옵션 추가)
    NoticePagingDTO getNoticeListWithPagination(int currentPage, String searchKeyword, String searchOption);

    // 전체 공지사항 수를 가져오는 메서드 (검색어 및 검색 옵션 추가)
    int getNoticeCount(String searchKeyword, String searchOption);

    NoticeDTO noticeview(long id);

    boolean increaseViews(long id);  // 조회수 증가 메서드
}
