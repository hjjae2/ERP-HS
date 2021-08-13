package com.hserp.dto.work;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@ToString
@Builder
@Getter
public class WorkRequestDto {

    private String workType;
    private String address;
    private String content;
    private String worker;
    private String company;
    private String remark;
    private String workStatus;
    private String paymentStatus;
    private String expenditureStatus;
    private String taxStatus;
    private Integer price;
    private LocalDate workDate;
}
