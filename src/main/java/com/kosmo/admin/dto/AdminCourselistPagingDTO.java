package com.kosmo.admin.dto;

import com.kosmo.notice.dto.NoticeDTO;

import java.util.List;

public class AdminCourselistPagingDTO {
    private Integer currentPage;  // 현재 페이지
    private int totalPages;       // 전체 페이지 수
    private int totalCount;       // 전체 게시물 수
    private int pageSize = 30;    // 한 페이지 당 게시물 수 (기본값 30로 변경)
    private List<NoticeDTO> noticeList;  // 공지사항 목록
    private int start;            // 페이징 시작 번호
    private int end;              // 페이징 끝 번호
    private int startPage;        // 페이지 그룹 시작 번호
    private int endPage;          // 페이지 그룹 끝 번호
    private String searchKeyword; // 검색 키워드
    private String searchOption; // 검색 옵션

    // 생성자에서 시작 페이지와 끝 페이지를 계산
    public AdminCourselistPagingDTO(Integer currentPage) {
        this.currentPage = (currentPage != null && currentPage > 0) ? currentPage : 1;  // 기본값 1
        this.start = (this.currentPage - 1) * pageSize;
        this.end = this.currentPage * pageSize;
    }

    // 전체 페이지 수 계산
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        this.totalPages = (int) Math.ceil((double) totalCount / pageSize);
        if (totalPages == 0) {  // 0페이지 처리
            totalPages = 1;
        }
    }

    // 페이지 그룹 계산 (예: 1~5, 6~10)
    public void calculatePageRange() {
        int groupSize = 5;  // 페이지 그룹 크기 (예: 1~5, 6~10, ...)
        this.startPage = ((currentPage - 1) / groupSize) * groupSize + 1;
        this.endPage = Math.min(startPage + groupSize - 1, totalPages);
    }

    // 검색 키워드와 옵션을 설정하는 메서드
    public void setSearchCriteria(String searchKeyword, String searchOption) {
        this.searchKeyword = searchKeyword;
        this.searchOption = searchOption;
    }
}

