package com.kosmo.notice.mapper;

import com.kosmo.notice.dto.NoticeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NoticeMapper {

    // 공지사항 저장
    int saveNotice(NoticeDTO noticeDTO);

    // 페이징된 공지사항 목록을 가져오는 쿼리 (검색어 및 검색 옵션 추가)
    List<NoticeDTO> getNoticeListWithPagination(
            @Param("start") int start,
            @Param("end") int end,
            @Param("searchKeyword") String searchKeyword,
            @Param("searchOption") String searchOption
    );

    // 전체 공지사항 수를 가져오는 쿼리 (검색어 및 검색 옵션 추가)
    int getTotalNoticeCount(
            @Param("searchKeyword") String searchKeyword,
            @Param("searchOption") String searchOption
    );
    NoticeDTO noticeview(long id);

    void increaseViews(long id);


}
