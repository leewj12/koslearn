package com.kosmo.admin.service;

import com.kosmo.admin.dto.AdminPaymentDTO;
import com.kosmo.admin.mapper.AdminPaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminPaymentServiceImpl implements AdminPaymentService {

    @Autowired
    private AdminPaymentMapper adminPaymentMapper;

    @Override
    public List<AdminPaymentDTO> payinfo() {
        // Mapper에서 결제 정보를 바로 가져옴
        return adminPaymentMapper.payinfo();
    }
}
