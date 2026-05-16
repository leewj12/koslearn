package com.kosmo.admin.service;

import com.kosmo.admin.dto.AdminPaymentDTO;

import java.util.List;

public interface AdminPaymentService {
    List<AdminPaymentDTO> payinfo();  // 결제 정보를 바로 가져오는 메소드
}
