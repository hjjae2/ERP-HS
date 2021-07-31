package com.hserp.dto.work;

import com.hserp.dto.company.CompanyResponseDto;
import com.hserp.dto.expenditure.ExpenditureResponseDto;
import com.hserp.dto.payment.PaymentResponseDto;
import com.hserp.dto.person.PersonResponseDto;
import com.hserp.dto.tax.TaxResponseDto;
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
    private PaymentResponseDto payment;
    private ExpenditureResponseDto expenditure;
    private TaxResponseDto tax;
    private CompanyResponseDto company;
    private PersonResponseDto worker;

    public static WorkResponseDto of(Work work) {
        return WorkResponseDto.builder()
                .id(work.getId())
                .price(work.getPrice())
                .address(work.getAddress())
                .content(work.getContent())
                .remark(work.getRemark())
                .workType(work.getType().getName())
                .workDate(LocalDate.from(work.getWorkDate()))
                .createdDate(work.getCreatedDate())
                .updatedDate(work.getUpdatedDate())
                .payment(PaymentResponseDto.of(work.getPayment()))
                .expenditure(ExpenditureResponseDto.of(work.getExpenditure()))
                .tax(TaxResponseDto.of(work.getTax()))
                .company(CompanyResponseDto.of(work.getCompany()))
                .worker(PersonResponseDto.of(work.getWorker()))
                .build();
    }
}
