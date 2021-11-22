package com.hserp.work.dto;

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

    private LocalDate workDate;
    private String workType;
    private String worker;
    private String company;
    private String customer;
    private String dispatcher;
    private String address;
    private String content;
    private String remark;
    private String paymentStatus;
    private String expenditureStatus;
    private String taxStatus;
    private Integer price;
    private Character communicator;
}
