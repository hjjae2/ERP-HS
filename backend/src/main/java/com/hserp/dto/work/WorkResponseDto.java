package com.hserp.dto.work;

import com.hserp.dto.company.CompanyResponseDto;
import com.hserp.dto.person.PersonResponseDto;
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
}
