package com.hserp.dto.payment;

import com.hserp.entity.payment.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PaymentResponseDto {

    private int id;
    private int targetPrice;
    private int currentPrice;
    private String status;
    private String remark;
    private String worker;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public static PaymentResponseDto of(Payment payment) {
        return PaymentResponseDto.builder()
                .id(payment.getId())
                .targetPrice(payment.getTargetPrice())
                .currentPrice(payment.getCurrentPrice())
                .status(payment.getStatus().getName())
                .remark(payment.getRemark())
                .worker(payment.getWorker().getName())
                .createdDate(payment.getCreatedDate())
                .updatedDate(payment.getUpdatedDate())
                .build();
    }
}
