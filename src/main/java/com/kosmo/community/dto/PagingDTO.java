package com.kosmo.community.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PagingDTO {
    private int currentPage;   // 현재 페이지 번호
    private int totalPages;    // 전체 페이지 수
    private int totalCount;    // 전체 데이터 수
    private int pageSize = 20; // 한 페이지당 데이터 수
    private int blockSize = 10; // 한 번에 표시할 페이지 수
    private List<CommunityDTO> communityList; // 페이지에 해당하는 게시글 목록

    private int start;  // 페이징 시작 인덱스
    private int end;    // 페이징 끝 인덱스
    private int startPage; // 표시할 페이지의 시작 번호
    private int endPage;  // 표시할 페이지의 끝 번호

    // 검색 필터와 키워드
    private String filterType; // 검색 유형
    private String query;      // 검색어
    private String category;

    // 생성자 (현재 페이지와 페이징 인덱스 계산)
    public PagingDTO(int currentPage) {
        this.currentPage = currentPage;
        calculatePagingIndexes();
    }

    // 전체 페이지 계산
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        this.totalPages = (int) Math.ceil((double) totalCount / pageSize);
        calculatePagingIndexes();
    }

    // 페이징 시작/끝 인덱스 계산
    private void calculatePagingIndexes() {
        this.start = (currentPage - 1) * pageSize;
        this.end = currentPage * pageSize;
        int currentBlock = (int) Math.ceil((double) currentPage / blockSize);
        this.startPage = (currentBlock - 1) * blockSize + 1;
        this.endPage = Math.min(currentBlock * blockSize, totalPages);
    }

    // 검색 필터와 키워드를 설정할 수 있는 메서드 추가
    public void setFilter(String filterType, String query) {
        this.filterType = filterType;
        this.query = query;
    }

    // 페이지 사이즈를 동적으로 설정할 수 있는 메서드
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        calculatePagingIndexes(); // 페이지 사이즈 변경 시 인덱스도 재계산
    }
}
