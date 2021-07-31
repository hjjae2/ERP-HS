package com.hserp.dto.company;

import com.hserp.entity.company.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CompanyResponseDto {

    private int id;
    private String name;
    private String remark;
    private String representative;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public static CompanyResponseDto of(Company company) {
        return CompanyResponseDto.builder()
                .id(company.getId())
                .name(company.getName())
                .remark(company.getRemark())
                .representative(company.getRep().getName())
                .createdDate(company.getCreatedDate())
                .updatedDate(company.getUpdatedDate())
                .build();
    }
}
