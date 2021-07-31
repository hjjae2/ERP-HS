package com.hserp.dto.tax;

import com.hserp.entity.tax.Tax;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class TaxResponseDto {

    private int id;
    private String issueStatus;
    private String sendingStatus;
    private String worker;
    private String remark;
    private LocalDate issueDate;
    private LocalDate sendingDate;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public static TaxResponseDto of(Tax tax) {
        return TaxResponseDto.builder()
                .id(tax.getId())
                .issueStatus(tax.getIssueStatus().getName())
                .sendingStatus(tax.getSendingStatus().getName())
                .worker(tax.getWorker().getName())
                .remark(tax.getRemark())
                .issueDate(tax.getIssueDate())
                .sendingDate(tax.getSendingDate())
                .createdDate(tax.getCreatedDate())
                .updatedDate(tax.getUpdatedDate())
                .build();
    }
}
