package com.kosmo.course.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CourseviewPageingDTO {
    private int currentPage;
    private int totalPages;
    private int totalCount;
    private int pageSize = 30;
    private List<CourseviewDTO> courseviewList;
    private int start;
    private int end;
    private int startPage;  // 페이지 그룹의 시작 번호
    private int endPage;    // 페이지 그룹의 끝 번호

    public CourseviewPageingDTO(int currentPage) {
        this.currentPage = currentPage;
        this.start = (currentPage - 1) * pageSize;
        this.end = currentPage * pageSize;
    }

    // 전체 페이지 계산
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        this.totalPages = (int) Math.ceil((double) totalCount / pageSize);
    }

    // 페이지 그룹 계산 (1~5, 6~10 등의 범위로 묶기)
    public void calculatePageRange() {
        int groupSize = 5; // 페이지 번호 그룹 크기
        this.startPage = ((currentPage - 1) / groupSize) * groupSize + 1;
        this.endPage = Math.min(startPage + groupSize - 1, totalPages);
    }

    public int getStartPage() {
        return startPage;
    }

    public int getEndPage() {
        return endPage;
    }
}