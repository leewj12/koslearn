package com.kosmo.notice.service;

import com.kosmo.notice.dto.NoticeDTO;
import com.kosmo.notice.dto.NoticePagingDTO;
import com.kosmo.notice.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public int saveNotice(NoticeDTO noticeDTO) {
        return noticeMapper.saveNotice(noticeDTO);
    }

    @Override
    public NoticePagingDTO getNoticeListWithPagination(int currentPage, String searchKeyword, String searchOption) {
        // 페이징 처리 객체 생성
        NoticePagingDTO pagingDTO = new NoticePagingDTO(currentPage);

        // 전체 공지사항 수 (검색어와 검색 옵션 포함)
        int totalCount = noticeMapper.getTotalNoticeCount(searchKeyword, searchOption);
        pagingDTO.setTotalCount(totalCount);

        // 페이지 범위 계산
        pagingDTO.calculatePageRange();

        // 페이징된 공지사항 목록 가져오기 (검색어와 검색 옵션 포함)
        pagingDTO.setNoticeList(noticeMapper.getNoticeListWithPagination(
                pagingDTO.getStart(), pagingDTO.getPageSize(), searchKeyword, searchOption
        ));

        return pagingDTO;
    }


    @Override
    public int getNoticeCount(String searchKeyword, String searchOption) {
        return noticeMapper.getTotalNoticeCount(searchKeyword, searchOption);
    }

    @Override
    public NoticeDTO noticeview(long id) {
        return noticeMapper.noticeview(id);
    }

    @Override
    public boolean increaseViews(long id) {
        try {
            noticeMapper.increaseViews(id);  // 조회수 증가 쿼리 실행
            return true;
        } catch (Exception e) {
            return false;  // 예외 발생 시 실패

        }
    }
}