package com.kosmo.purchase.controller;

import com.kosmo.purchase.dto.PayDTO;
import com.kosmo.purchase.dto.PayMentDTO;
import com.kosmo.purchase.dto.PurchaseInfoDTO;
import com.kosmo.purchase.service.PurchaseInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
public class PurchaseController {


    @Autowired
    private PurchaseInfoService purchaseInfoService;

    //결제페이지
    @GetMapping("/cart/purchase")
    public String purchaseCourse() {
        return "purchase/purchasePage";  // 실제 구매 페이지로 리다이렉트
    }

    //사용자 정보와 장바구니 내용 결제창으로 보내주기
    @GetMapping("/payment/start/{userId}")
    public String startPayment(
            @PathVariable Long userId,
            @RequestParam String courseIds,
            @RequestParam String courseTitles,
            Model model) {

        // courseIds와 courseTitles를 쉼표로 구분하여 배열로 변환
        String[] courseIdArray = courseIds.split(",");
        String[] courseTitleArray = courseTitles.split(",");


        // 각 배열을 출력해서 확인
        System.out.println("User ID: " + userId);
        System.out.println("Selected Course IDs: " + Arrays.toString(courseIdArray));
        System.out.println("Selected Course Titles: " + Arrays.toString(courseTitleArray));

        // 장바구니에서 결제 정보 가져오기
        List<PurchaseInfoDTO> purchaseInfoList = purchaseInfoService.getPurchaseInfo(userId, courseIdArray);

        log.info("이제 마지막 서막으로 가보자============================{}", purchaseInfoList);

        // 결제 정보 목록을 Model에 추가
        model.addAttribute("purchaseInfoList", purchaseInfoList);

        return "purchase/paymentPage";  // 결제 페이지로 이동
    }


    //결제 시스템
    @PostMapping("/payment/submit")
    public String payment(PayDTO payDTO, Model model) {
        log.info("결제에 무슨 데이터가 들어오는지 확인해보자 =================================={}", payDTO);

        // 결제 처리 후 결과를 받음
        int paysuccess = purchaseInfoService.insertPaymentInfo(payDTO);

        String msg;
        String loc;

        if (paysuccess > 0) {
            // 결제가 성공하면 장바구니 데이터 삭제
            purchaseInfoService.deleteCartItems(payDTO);

            msg = "결제에 성공하였습니다! 감사합니다!";
            loc = "/";  // 결제 성공 시 이동할 경로
        } else {
            // 결제 실패 시 실패 페이지로 이동
            msg = "결제에 실패하였습니다! 다시 시도해주시기 바랍니다!";
            loc = "/";  // 결제 실패 시 이동할 경로
        }

        // Model에 msg와 loc 값을 추가하여 Thymeleaf로 전달
        model.addAttribute("msg", msg);
        model.addAttribute("loc", loc);

        return "/error/pay";  // utility 페이지로 이동
    }
}
