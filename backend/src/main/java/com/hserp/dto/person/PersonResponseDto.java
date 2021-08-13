package com.hserp.dto.person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@ToString
@Getter
public class PersonResponseDto {

    private Integer id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String remark;
    private String company;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
