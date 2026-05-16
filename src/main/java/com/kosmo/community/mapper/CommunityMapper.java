package com.kosmo.community.mapper;

import com.kosmo.community.dto.CommunityDTO;
import com.kosmo.community.dto.CommunityReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommunityMapper {
    int insertPost(CommunityDTO communityDTO); // 글 작성
    CommunityDTO getPostById(Long postId); //id로 정보찾기
    void increaseViewCount(Long postId);
    int getViewCount(Long postId);
    void updateboard(CommunityDTO communityDTO);
    void deleteboard(long postId);
    List<CommunityDTO> selectPostsByCategory(String category, int startPost, int postsPerPage);
    List<CommunityDTO> selectPostsByFilter(String filterType, String query, int startPost, int postsPerPage);
    int countPostsByCategory(String category);
    void insertreview(CommunityReviewDTO communityReviewDTO);
    List<CommunityReviewDTO> getReviewBypostId(long postId);
    void deletereview(long review_id);
}