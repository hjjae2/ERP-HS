package com.hserp.person.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
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

