package com.kosmo.purchase.mapper;

import com.kosmo.purchase.dto.PayDTO;
import com.kosmo.purchase.dto.PayMentDTO;
import com.kosmo.purchase.dto.PurchaseInfoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PurchaseInfoMapper {
    List<PurchaseInfoDTO> getPurchaseInfo(@Param("userId") Long userId, @Param("courseIds") String[] courseIds);

    int insertPaymentInfo(PayDTO PayDTO);

    void deleteCartItems(PayDTO PayDTO);

}
