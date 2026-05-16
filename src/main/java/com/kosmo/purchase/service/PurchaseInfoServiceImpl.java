package com.kosmo.purchase.service;

import com.kosmo.purchase.dto.PayDTO;
import com.kosmo.purchase.dto.PayMentDTO;
import com.kosmo.purchase.dto.PurchaseInfoDTO;
import com.kosmo.purchase.mapper.PurchaseInfoMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseInfoServiceImpl implements PurchaseInfoService {

    @Autowired
    private PurchaseInfoMapper purchaseInfoMapper;

    @Override
    public List<PurchaseInfoDTO> getPurchaseInfo(Long userId, String[] courseIds) {
        return purchaseInfoMapper.getPurchaseInfo(userId, courseIds);
    }

    @Transactional
    public int insertPaymentInfo(PayDTO PayDTO) {
        int result = purchaseInfoMapper.insertPaymentInfo(PayDTO);
        return result; // 성공 시 1, 실패 시 0 반환
    }

    @Override
    public void deleteCartItems(PayDTO payDTO) {
        purchaseInfoMapper.deleteCartItems(payDTO); // `return` 제거 및 괄호 수정
    }
}
