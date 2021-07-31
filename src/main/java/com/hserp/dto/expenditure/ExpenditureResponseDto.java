package com.hserp.dto.expenditure;

import com.hserp.entity.expenditure.Expenditure;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ExpenditureResponseDto {

    private int id;
    private int targetPrice;
    private int currentPrice;
    private String status;
    private String remark;
    private String worker;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public static ExpenditureResponseDto of(Expenditure expenditure) {
        return ExpenditureResponseDto.builder()
                .id(expenditure.getId())
                .targetPrice(expenditure.getTargetPrice())
                .currentPrice(expenditure.getCurrentPrice())
                .status(expenditure.getStatus().getName())
                .remark(expenditure.getRemark())
                .worker(expenditure.getWorker().getName())
                .createdDate(expenditure.getCreatedDate())
                .updatedDate(expenditure.getUpdatedDate())
                .build();
    }
}
