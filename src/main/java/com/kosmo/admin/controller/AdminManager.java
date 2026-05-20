package com.kosmo.admin.controller;

import com.kosmo.admin.dto.AdminCourselistDTO;
import com.kosmo.admin.dto.AdminPaymentDTO;
import com.kosmo.admin.service.AdminCourselistService;
import com.kosmo.admin.service.AdminPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminManager {

    @Autowired
    private AdminCourselistService adminCourselistService;

    @Autowired
    private AdminPaymentService adminPaymentService;

    @GetMapping("/admin/home")
    public String admindashboard() {
        return "admin/admindashboard";  // admin/admindashboard.html을 반환
    }

    @GetMapping("/admin/courselist")
    public String admincourselist(Model model) {
        List<AdminCourselistDTO> courselist = adminCourselistService.Courselist();  // 강의 목록 조회
        model.addAttribute("courselist", courselist);  // 강의 목록을 모델에 추가
        return "admin/admincourselist";  // 뷰로 전달
    }

    @GetMapping("/admin/payment")
    public String adminPayment(Model model) {
        // 결제 정보를 서비스에서 바로 가져옴
        List<AdminPaymentDTO> adminPaymentDTO = adminPaymentService.payinfo();
        // 모델에 데이터를 담을 때 이름을 'adminPaymentList'로 변경
        model.addAttribute("adminPaymentList", adminPaymentDTO);

        // "/admin/payment" 페이지로 이동
        return "admin/payment";
    }


    @PostMapping("/admin/course/delete/{id}")
    public String deleteCourse(@PathVariable("id") Long courseId) {
        // courseId를 이용해 해당 강의를 삭제하는 로직을 구현
        adminCourselistService.deleteCourse(courseId);  // 예시로 삭제 서비스 호출

        // 삭제 후 목록 페이지로 리다이렉트
        return "redirect:/admin/courselist";  // 삭제 후 목록 페이지로 리다이렉트
    }

    @GetMapping("/admin/course/detail/{id}")
    public String detail(@PathVariable("id") Long courseId, Model model) {
        AdminCourselistDTO courseDetail = adminCourselistService.Coursedetail(courseId);
        model.addAttribute("course", courseDetail);  // Add the course details to the model
        return "course/coursedetail";  // Returns the view name
    }

    @GetMapping("/course/edit/{id}")
    public String courseEdit(@PathVariable("id") Long courseId) {
        // Fetch course details using the service
        AdminCourselistDTO courseDetail = adminCourselistService.Coursedetail(courseId);

//        // Add the course details to the model so it can be accessed in the view
//        model.addAttribute("course", courseDetail);

        // Return the view name for editing course
        return "course/courseedit";

    }








}

