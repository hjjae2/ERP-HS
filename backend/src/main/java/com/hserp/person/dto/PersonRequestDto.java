package com.hserp.person.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Builder
@ToString
@Getter
public class PersonRequestDto {

    private String name;
    private String phone;
    private String email;
    private String address;
    private String remark;
    private String company;
}
