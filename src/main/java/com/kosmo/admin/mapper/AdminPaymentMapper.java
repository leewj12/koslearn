package com.kosmo.admin.mapper;

import com.kosmo.admin.dto.AdminPaymentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminPaymentMapper {
    List<AdminPaymentDTO> payinfo();
}
