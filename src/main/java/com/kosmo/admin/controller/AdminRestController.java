package com.kosmo.admin.controller;
import com.kosmo.admin.dto.MemberDTO;
import com.kosmo.admin.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api/users")
@RequiredArgsConstructor
public class AdminRestController {

    private final MemberService memberService;

    @GetMapping("/info")
    public MemberDTO getUserInfo(@RequestParam("id") Long id) {
        return memberService.getUsers(id); // JSON 데이터 반환
    }
}
