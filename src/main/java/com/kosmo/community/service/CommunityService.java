package com.kosmo.community.service;

import com.kosmo.community.dto.CommunityDTO;
import com.kosmo.community.dto.CommunityReviewDTO;

import java.util.List;

public interface CommunityService {

    int writePost(CommunityDTO communityDTO); // 글쓰기 처리
    CommunityDTO getPostById(Long postId);
    void increaseViewCount(Long postId);
    int getViewCount(Long postId);
    void updateboard(CommunityDTO communityDTO);
    void deleteboard(long postId);
    List<CommunityDTO> getPostsByCategory(String category, int startPost, int postsPerPage);
    int countPostsByCategory(String category);
    List<CommunityDTO> getPostsByFilter(String filterType, String query, int startPost, int postsPerPage);
    void insertreview(CommunityReviewDTO communityReviewDTO);
    List<CommunityReviewDTO> getReviewBypostId(long postId);
    void deletereview(long review_id);

}