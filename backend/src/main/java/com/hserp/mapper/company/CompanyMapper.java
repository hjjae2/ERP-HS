package com.hserp.mapper.company;

import com.hserp.dto.company.CompanyRequestDto;
import com.hserp.dto.company.CompanyResponseDto;
import com.hserp.entity.company.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CompanyMapper {
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    @Mapping(source = "phone.value", target = "phone")
    @Mapping(source = "email.value", target = "email")
    @Mapping(source = "address.value", target = "address")
    @Mapping(source = "remark.value", target = "remark")
    @Mapping(source = "company.name", target = "company")
    CompanyRequestDto companyToCompanyRequestDto(Company company);

    @Mapping(source = "phone.value", target = "phone")
    @Mapping(source = "email.value", target = "email")
    @Mapping(source = "address.value", target = "address")
    @Mapping(source = "remark.value", target = "remark")
    @Mapping(source = "company.name", target = "company")
    CompanyResponseDto companyToCompanyResponseDto(Company company);

    @Mapping(source = "phone", target = "phone.value")
    @Mapping(source = "email", target = "email.value")
    @Mapping(source = "address", target = "address.value")
    @Mapping(source = "remark", target = "remark.value")
    @Mapping(source = "company", target = "company.name")
    Company companyRequestDtoToCompany(CompanyRequestDto companyRequestDto);

    @Mapping(source = "phone", target = "phone.value")
    @Mapping(source = "email", target = "email.value")
    @Mapping(source = "address", target = "address.value")
    @Mapping(source = "remark", target = "remark.value")
    @Mapping(source = "company", target = "company.name")
    Company companyResponseDtoToCompany(CompanyResponseDto companyResponseDto);
}
