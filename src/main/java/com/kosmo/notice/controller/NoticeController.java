package com.kosmo.notice.controller;

import com.kosmo.notice.dto.NoticeDTO;
import com.kosmo.notice.dto.NoticePagingDTO;
import com.kosmo.notice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    // 모든 사람들이 공지사항 보는 곳
    @GetMapping("/noticeview")
    public String noticeView(
            @RequestParam(defaultValue = "1") int currentPage,
            @RequestParam(defaultValue = "") String searchKeyword,
            @RequestParam(defaultValue = "all") String searchOption,
            Model model) {

        try {
            NoticePagingDTO pagingDTO = noticeService.getNoticeListWithPagination(currentPage, searchKeyword, searchOption);
            model.addAttribute("pagingDTO", pagingDTO);
            model.addAttribute("noticeList", pagingDTO.getNoticeList());
        } catch (Exception e) {
            model.addAttribute("pagingDTO", null);
        }
        model.addAttribute("searchKeyword", searchKeyword);
        model.addAttribute("searchOption", searchOption);

        return "/notice/notice";
    }

    // 관리자가 공지사항 만드는 곳
    @GetMapping("/admin/notice")
    public String adminnotice() {
        return "/notice/adminnotice";
    }



    // 관리자가 글 올리는 곳
    @PostMapping("/admin/notice")
    public String createNotice(@ModelAttribute NoticeDTO noticeDTO, Model model,
                               @RequestParam("thumbnailImage") MultipartFile thumbnailImage,
                               @RequestParam("bannerImage") MultipartFile bannerImage) {
        // 파일 업로드 디렉토리 설정
        String uploadDir = "src/main/resources/notice";
        Path uploadPath = Paths.get(uploadDir);

        try {
            // 디렉토리가 없으면 생성
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // 썸네일 이미지 처리 (UUID + 원본 파일명)
            if (!thumbnailImage.isEmpty()) {
                String originalThumbnailFileName = thumbnailImage.getOriginalFilename();
                String hashedThumbnailFileName = UUID.randomUUID() + "_" + originalThumbnailFileName;

                // 썸네일 이미지 저장
                Path thumbnailFilePath = uploadPath.resolve(hashedThumbnailFileName);
                thumbnailImage.transferTo(thumbnailFilePath); // MultipartFile을 파일로 저장

                // DTO에 파일 정보 설정
                noticeDTO.setOriginalThumbnailImage(originalThumbnailFileName);
                noticeDTO.setHashedThumbnailImage(hashedThumbnailFileName);
            }

            // 배너 이미지 처리 (UUID + 원본 파일명)
            if (!bannerImage.isEmpty()) {
                String originalBannerImageFileName = bannerImage.getOriginalFilename();
                String hashedBannerImageFileName = UUID.randomUUID() + "_" + originalBannerImageFileName;

                // 배너 이미지 저장
                Path bannerImagePath = uploadPath.resolve(hashedBannerImageFileName);
                bannerImage.transferTo(bannerImagePath); // MultipartFile을 파일로 저장

                // DTO에 파일 정보 설정
                noticeDTO.setOriginalBannerImage(originalBannerImageFileName);
                noticeDTO.setHashedBannerImage(hashedBannerImageFileName);
            }

            // 공지사항 저장
            int result = noticeService.saveNotice(noticeDTO);

            // 성공 여부 확인
            if (result > 0) {
                model.addAttribute("message", "공지사항이 등록되었습니다.");
            } else {
                model.addAttribute("error", "공지사항 등록에 실패했습니다.");
            }

        } catch (IOException e) {
            model.addAttribute("error", "파일 업로드 중 오류가 발생했습니다.");
        }

        return "redirect:/noticeview";
    }

    @GetMapping("/noticeview/{id}")
    public String getNoticeDetails(@PathVariable Long id, Model model) {


        boolean updated = noticeService.increaseViews(id);
        NoticeDTO noticeviewall = noticeService.noticeview(id);
        model.addAttribute("noticeviewall", noticeviewall);  // NoticeDTO 객체를 모델에 추가
        return "/notice/noticeview";
    }



}
