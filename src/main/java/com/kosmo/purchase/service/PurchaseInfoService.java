package com.kosmo.purchase.service;

import com.kosmo.purchase.dto.PayDTO;
import com.kosmo.purchase.dto.PayMentDTO;
import com.kosmo.purchase.dto.PurchaseInfoDTO;
import java.util.List;

public interface PurchaseInfoService {
    List<PurchaseInfoDTO> getPurchaseInfo(Long userId, String[] courseIds);

   int insertPaymentInfo(PayDTO PayDTO);

    void deleteCartItems(PayDTO PayDTO);
}