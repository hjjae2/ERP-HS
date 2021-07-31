package com.hserp.dto.person;

import com.hserp.entity.person.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PersonResponseDto {

    private int id;
    private String name;
    private String remark;
    private String company;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public static PersonResponseDto of(Person person) {
        return PersonResponseDto.builder()
                .name(person.getName())
                .remark(person.getRemark())
                .company(person.getCompany().getName())
                .createdDate(person.getCreatedDate())
                .updatedDate(person.getUpdatedDate())
                .build();
    }
}
