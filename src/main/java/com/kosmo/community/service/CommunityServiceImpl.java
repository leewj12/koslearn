package com.kosmo.community.service;

import com.kosmo.community.dto.CommunityDTO;
import com.kosmo.community.dto.CommunityReviewDTO;
import com.kosmo.community.mapper.CommunityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityServiceImpl implements CommunityService{

    private final CommunityMapper communityMapper;

    @Autowired
    public CommunityServiceImpl(CommunityMapper communityMapper) {
        this.communityMapper = communityMapper;
    }

    @Override
    public int writePost(CommunityDTO communityDTO) {
        // 글 작성 로직 처리
        return communityMapper.insertPost(communityDTO);
    }

    @Override
    public CommunityDTO getPostById(Long postId) {
        return communityMapper.getPostById(postId);
    }

    @Override
    public void increaseViewCount(Long postId) {
        communityMapper.increaseViewCount(postId);
    }

    @Override
    public int getViewCount(Long postId) {
        communityMapper.increaseViewCount(postId);
        return communityMapper.getViewCount(postId);
    }

    @Override
    public void updateboard(CommunityDTO communityDTO) {
        communityMapper.updateboard(communityDTO);
    }

    @Override
    public void deleteboard(long postId) {
        communityMapper.deleteboard(postId);
    }

    @Override
    public List<CommunityDTO> getPostsByCategory(String category, int startPost, int postsPerPage) {
        return communityMapper.selectPostsByCategory(category, startPost, postsPerPage);
    }

    @Override
    public int countPostsByCategory(String category) {
        return communityMapper.countPostsByCategory(category);
    }

    @Override
    public List<CommunityDTO> getPostsByFilter(String filterType, String query, int startPost, int postsPerPage) {
        return communityMapper.selectPostsByFilter(filterType, query, startPost, postsPerPage);
    }

    @Override
    public void insertreview(CommunityReviewDTO communityReviewDTO) {
        communityMapper.insertreview(communityReviewDTO);
    }

    @Override
    public List<CommunityReviewDTO> getReviewBypostId(long postId) {
        List<CommunityReviewDTO> communityReviewDTOS=communityMapper.getReviewBypostId(postId);
        return communityReviewDTOS;
    }

    @Override
    public void deletereview(long review_id) {
        communityMapper.deletereview(review_id);
    }
}
