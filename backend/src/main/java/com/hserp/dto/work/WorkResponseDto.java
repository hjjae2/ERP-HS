package com.hserp.dto.work;

import com.hserp.dto.company.CompanyResponseDto;
import com.hserp.dto.person.PersonResponseDto;
import com.hserp.entity.work.ExpenditureStatus;
import com.hserp.entity.work.PaymentStatus;
import com.hserp.entity.work.TaxStatus;
import com.hserp.entity.work.Work;
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
public class WorkResponseDto {

    private int id;
    private int price;
    private String address;
    private String content;
    private String remark;
    private String workType;
    private LocalDate workDate;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String paymentStatus;
    private String expenditureStatus;
    private String taxStatus;
    private CompanyResponseDto company;
    private PersonResponseDto worker;

    public static WorkResponseDto of(Work work) {
        return WorkResponseDto.builder()
                .id(work.getId())
                .price(work.getPrice())
                .address(work.getAddress().getValue())
                .content(work.getContent())
                .remark(work.getRemark().getValue())
                .workType(work.getType().getName())
                .workDate(LocalDate.from(work.getWorkDate()))
                .createdDate(work.getCreatedDate())
                .updatedDate(work.getUpdatedDate())
                .paymentStatus(work.getPaymentStatus().getName())
                .expenditureStatus(work.getExpenditureStatus().getName())
                .taxStatus(work.getTaxStatus().getName())
                .company(CompanyResponseDto.of(work.getCompany()))
                .worker(PersonResponseDto.of(work.getWorker()))
                .build();
    }
}
