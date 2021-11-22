package com.hserp.company.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@ToString
@Getter
public class CompanyResponseDto {

    private Integer id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String remark;
    private String manager;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
