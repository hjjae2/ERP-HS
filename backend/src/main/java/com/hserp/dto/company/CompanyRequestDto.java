package com.hserp.dto.company;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class CompanyRequestDto {

    private String name;
    private String phone;
    private String email;
    private String address;
    private String remark;
    private String manager;
}
