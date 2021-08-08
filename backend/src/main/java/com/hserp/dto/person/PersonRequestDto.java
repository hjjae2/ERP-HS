package com.hserp.dto.person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
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
