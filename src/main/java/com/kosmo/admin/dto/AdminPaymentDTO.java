package com.kosmo.admin.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AdminPaymentDTO {
    private int payment_id;              // payment_id (결제 고유 ID)
    private BigDecimal amount;          // amount (결제 금액)
    private String username;            // username (사용자 이름)
    private String title;          // courseName (강의 이름, 오타 수정)
    private LocalDateTime payment_date;  // payment_date (결제 일시)
    private String payment_Method;       // payment_method (결제 방법)
    private String payment_Status;       // paymentStatus (결제 상태, 오타 수정)

    public String getCreatedAtFormatted() {
        if (payment_date != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return payment_date.format(formatter);  // 원하는 포맷으로 변환하여 반환
        }
        return null;  // 또는 ""로 반환할 수 있습니

    }

}