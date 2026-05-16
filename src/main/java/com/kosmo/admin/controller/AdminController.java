package com.kosmo.admin.controller;

import com.kosmo.admin.dto.MemberPagingDTO;
import com.kosmo.admin.service.MemberService;
import com.kosmo.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    @Autowired
    private MemberService memberService;

    /**
     * 사용자 목록 및 검색
     */
    @GetMapping("/admin/users")
    public String adminUser(
            Model model,
            @RequestParam(defaultValue = "all") String filterType,
            @RequestParam(defaultValue = "") String query,
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "10") Integer pageSize) {

        if (currentPage == null || currentPage <= 0) {
            currentPage = 1;
        }

        // 페이징 DTO 생성 및 검색 조건 설정
        MemberPagingDTO pagingDTO = new MemberPagingDTO(currentPage, pageSize);
        pagingDTO.setFilterType(filterType);
        pagingDTO.setQuery(query);

        // 페이징 및 검색 데이터 가져오기
        pagingDTO = memberService.listMember(pagingDTO);

        // 모델에 데이터 추가
        model.addAttribute("filterType", filterType);
        model.addAttribute("query", query);
        model.addAttribute("paging", pagingDTO);
        model.addAttribute("members", pagingDTO.getMembers());
        model.addAttribute("totalCount", pagingDTO.getTotalCount());
        model.addAttribute("pageSize", pageSize);

        return "admin/users";
    }

    /**
     * 사용자 검색 (추가된 핸들러)
     */
    @GetMapping("/admin/users/search")
    public String searchUsers(Model model,
                              @RequestParam(defaultValue = "all") String filterType,
                              @RequestParam(defaultValue = "") String query,
                              @RequestParam(defaultValue = "1") Integer currentPage,
                              @RequestParam(defaultValue = "10") Integer pageSize) {
        if (currentPage == null || currentPage <= 0) {
            currentPage = 1;
        }

        // 페이징 DTO 생성 및 검색 조건 설정
        MemberPagingDTO pagingDTO = new MemberPagingDTO(currentPage, pageSize);
        pagingDTO.setFilterType(filterType);
        pagingDTO.setQuery(query);

        // 페이징 및 검색 데이터 가져오기
        pagingDTO = memberService.listMember(pagingDTO);

        // 모델에 데이터 추가
        model.addAttribute("filterType", filterType);
        model.addAttribute("query", query);
        model.addAttribute("paging", pagingDTO);
        model.addAttribute("members", pagingDTO.getMembers());
        model.addAttribute("totalCount", pagingDTO.getTotalCount());
        model.addAttribute("pageSize", pageSize);

        return "admin/users"; // 동일한 뷰를 재사용
    }

    /**
     * 사용자 수정
     */
    @PostMapping("/admin/users/update")
    public String updateUser(@RequestParam Long id,
                             @ModelAttribute UserDTO userDTO,
                             @RequestParam(required = false, defaultValue = "1") int currentPage,
                             @RequestParam(required = false, defaultValue = "all") String filterType,
                             @RequestParam(required = false, defaultValue = "") String query,
                             @RequestParam(required = false, defaultValue = "10") int pageSize) {
        // 사용자 정보 업데이트
        userDTO.setId(id);
        memberService.updateMemberById(userDTO);

        // 리다이렉트 시 검색어, 필터, 현재 페이지, 페이지 크기 유지
        return "redirect:/admin/users?currentPage=" + currentPage + "&filterType=" + filterType
                + "&query=" + query + "&pageSize=" + pageSize;
    }

    /**
     * 사용자 삭제
     */
    @PostMapping("/admin/users/delete")
    public String deleteUser(@RequestParam Long id,
                             @RequestParam(required = false, defaultValue = "1") int currentPage,
                             @RequestParam(required = false, defaultValue = "all") String filterType,
                             @RequestParam(required = false, defaultValue = "") String query,
                             @RequestParam(required = false, defaultValue = "10") int pageSize) {
        // 사용자 삭제
        memberService.deleteMemberById(id);

        // 리다이렉트 시 검색어, 필터, 현재 페이지, 페이지 크기 유지
        return "redirect:/admin/users?currentPage=" + currentPage + "&filterType=" + filterType
                + "&query=" + query + "&pageSize=" + pageSize;
    }

    @GetMapping("admin/users/info")
    public String adminUserInfo(@RequestParam("id") Long id) {
        return "admin/userInfo"; // 페이지 이동
    }
}