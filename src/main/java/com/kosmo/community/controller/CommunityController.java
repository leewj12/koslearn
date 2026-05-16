package com.kosmo.community.controller;

import com.kosmo.community.dto.CommunityDTO;
import com.kosmo.community.dto.CommunityReviewDTO;
import com.kosmo.community.dto.PagingDTO;
import com.kosmo.community.service.CommunityService;
import com.kosmo.community.service.PagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
public class CommunityController {
    @Autowired
    private PagingService pagingService;
    private final CommunityService communityService;

    @Autowired
    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    @GetMapping("/community/list")
    public String community(Model model,
                            @RequestParam(defaultValue = "all") String filterType,
                            @RequestParam(defaultValue = "") String query,
                            @RequestParam(defaultValue = "1") Integer currentPage,
                            @RequestParam(defaultValue = "all") String category) {  // 카테고리 파라미터 추가

        // 페이지 번호가 0 이하일 경우 1로 설정
        if (currentPage == null || currentPage <= 0) {
            currentPage = 1;
        }

        // PagingDTO 객체 생성 후 필터링 조건 설정
        PagingDTO pagingDTO = new PagingDTO(currentPage);
        pagingDTO.setFilterType(filterType);  // 필터 타입 설정
        pagingDTO.setQuery(query);  // 검색어 설정
        pagingDTO.setCategory(category);  // 카테고리 설정 (새로운 필드 추가)

        // 필터링 및 페이징된 데이터 조회
        pagingDTO = pagingService.getCommunityList(pagingDTO);  // 서비스 호출

        // 모델에 데이터 추가
        model.addAttribute("filterType", filterType);
        model.addAttribute("query", query);
        model.addAttribute("paging", pagingDTO);
        model.addAttribute("posts", pagingDTO.getCommunityList());  // 게시글 리스트
        model.addAttribute("totalCount", pagingDTO.getTotalCount());  // 전체 데이터 수
        model.addAttribute("category", category);  // 선택된 카테고리 값 추가

        return "community/community";  // 동일한 뷰 반환
    }

    @GetMapping("/community/post")
    public String communityPost() {
        return "community/communityPost";
    }

    @PostMapping("/community/write")
    public String communityWrite(
            CommunityDTO communityDTO,
            @RequestParam("fileName") MultipartFile file, // 파일 업로드 처리
            Model model) {

        // 업로드 경로 설정 (static 디렉토리를 사용)
        String uploadDir = "src/main/resources/communityupload/files";
        Path uploadPath = Paths.get(uploadDir);

        try {
            // 디렉토리가 없으면 생성
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            if (!file.isEmpty()) {
                // 파일 크기 검증
                if (file.getSize() > 100 * 1024 * 1024) {
                    model.addAttribute("message", "파일 크기는 100MB를 초과할 수 없습니다.");
                    return "community/communityPost";
                }

                // 파일명 처리 (UUID + 원본 파일명)
                String originalFileName = file.getOriginalFilename();
                String uuidFileName = UUID.randomUUID() + "_" + originalFileName;

                // 파일 저장
                Path filePath = uploadPath.resolve(uuidFileName);
                Files.write(filePath, file.getBytes());

                // DTO에 파일 정보 설정
                communityDTO.setOriginFileName(originalFileName);
                communityDTO.setUuidFileName(uuidFileName);
                communityDTO.setFileSize(file.getSize());
            }

            // mode 설정
            communityDTO.setMode("write");

            // Service 호출
            int result = communityService.writePost(communityDTO);
            if (result > 0) {
                return "redirect:/community/list"; // 성공 시 목록 페이지로 이동
            } else {
                model.addAttribute("message", "글 작성에 실패했습니다.");
                return "community/communityPost";
            }

        } catch (IOException e) {
            model.addAttribute("message", "파일 업로드에 실패했습니다.");
            return "community/communityPost";
        }
    }
    @GetMapping("/community/communityview/{postId}")
    public String getPostDetails(@PathVariable Long postId, Model model) {
        // 게시글 ID를 이용해 DB에서 해당 게시글 정보를 가져옴
        CommunityDTO post = communityService.getPostById(postId);

        // 게시글 정보를 뷰에 전달
        int n=communityService.getViewCount(postId);
        model.addAttribute("post", post);
        model.addAttribute("comments",communityService.getReviewBypostId(postId));
        return "community/communityview"; // 게시글 상세 페이지
    }
    @PostMapping("/community/edit")
    public String postboard(CommunityDTO communityDTO,@RequestParam("fileName") MultipartFile file,Model model) {
        // 업로드 경로 설정 (static 디렉토리를 사용)
        String uploadDir = "src/main/resources/communityupload/files";
        Path uploadPath = Paths.get(uploadDir);

        try {
            // 디렉토리가 없으면 생성
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            if (!file.isEmpty()) {
                // 파일 크기 검증
                if (file.getSize() > 100 * 1024 * 1024) {
                    model.addAttribute("message", "파일 크기는 100MB를 초과할 수 없습니다.");
                    return "community/communityview";
                }

                // 파일명 처리 (UUID + 원본 파일명)
                String originalFileName = file.getOriginalFilename();
                String uuidFileName = UUID.randomUUID() + "_" + originalFileName;

                // 파일 저장
                Path filePath = uploadPath.resolve(uuidFileName);
                Files.write(filePath, file.getBytes());

                // DTO에 파일 정보 설정
                communityDTO.setOriginFileName(originalFileName);
                communityDTO.setUuidFileName(uuidFileName);
                communityDTO.setFileSize(file.getSize());
            }
            communityService.updateboard(communityDTO);
            return "redirect:/community/list";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/community/delete")
    public String deleteboard(@RequestParam long postId){
        communityService.deleteboard(postId);
        return "redirect:/community/list";
    }
    @PostMapping("/community/comment")
    public String review(CommunityReviewDTO communityReviewDTO,@RequestParam long postId){
        communityService.insertreview(communityReviewDTO);
        return "redirect:/community/communityview/" + communityReviewDTO.getPostId();
    }
    @PostMapping("/delete")
    public String deletereview(@RequestParam long review_id, @RequestParam long postId) {
        // 댓글 삭제 서비스 호출
        communityService.deletereview(review_id);
        // 삭제 후 해당 게시물로 리다이렉트
        return "redirect:/community/communityview/" + postId;
    }


}
