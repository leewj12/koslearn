package com.kosmo.admin.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MemberPagingDTO {
    private int currentPage;   // 현재 페이지 번호
    private int totalPages;    // 전체 페이지 수
    private int totalCount;    // 전체 데이터 수
    private int pageSize;      // 한 페이지당 데이터 수
    private List<MemberDTO> members; // 페이지에 해당하는 사용자 목록

    private int start;  // 페이징 시작 인덱스
    private int end;    // 페이징 끝 인덱스

    private String filterType; // 검색 유형
    private String query;      // 검색어

    // 생성자 (기본 페이지 크기: 10)
    public MemberPagingDTO(int currentPage) {
        this(currentPage, 10); // 기본값 10
    }

    public MemberPagingDTO(int currentPage, int pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.start = (currentPage - 1) * pageSize;
        this.end = currentPage * pageSize;
    }

    // 전체 페이지 계산
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        this.totalPages = (int) Math.ceil((double) totalCount / pageSize);
    }
}