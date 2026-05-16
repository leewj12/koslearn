package com.kosmo.community.controller;

import com.kosmo.community.dto.CommunityDTO;
import com.kosmo.community.service.CommunityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class CommunityRestController {

    @Autowired
    private CommunityService communityService;

    @GetMapping("/community/filter")
    public ResponseEntity<Map<String, Object>> getFilteredPosts(
            @RequestParam(value = "category", defaultValue = "all") String category,
            @RequestParam(value = "filterType", defaultValue = "all") String filterType,
            @RequestParam(value = "query", defaultValue = "") String query,
            @RequestParam(value = "page", defaultValue = "1") int page) {

        int postsPerPage = 10;  // 한 페이지에 표시할 게시물 수
        int startPost = (page - 1) * postsPerPage;  // 페이지 시작 위치

        // 카테고리별 게시물 목록 가져오기
        List<CommunityDTO> posts;
        if ("all".equals(category)) {
            posts = communityService.getPostsByFilter(filterType, query, startPost, postsPerPage);
        } else {
            posts = communityService.getPostsByCategory(category, startPost, postsPerPage);
        }

        // 총 게시물 수와 페이지네이션 정보 계산
        int totalPosts = communityService.countPostsByCategory(category);
        int totalPages = (int) Math.ceil((double) totalPosts / postsPerPage);

        // 결과를 Map으로 반환
        Map<String, Object> response = new HashMap<>();
        response.put("posts", posts);
        response.put("totalPages", totalPages);

        return ResponseEntity.ok(response);
    }
}
