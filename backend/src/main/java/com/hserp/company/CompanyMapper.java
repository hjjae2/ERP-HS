package com.hserp.company;

import com.hserp.company.dto.CompanyRequestDto;
import com.hserp.company.dto.CompanyResponseDto;
import com.hserp.company.domain.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CompanyMapper {
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    @Mapping(source = "phone.value", target = "phone")
    @Mapping(source = "email.value", target = "email")
    @Mapping(source = "address.value", target = "address")
    @Mapping(source = "remark.value", target = "remark")
    @Mapping(source = "manager.name", target = "manager")
    CompanyRequestDto companyToCompanyRequestDto(Company company);

    @Mapping(source = "phone.value", target = "phone")
    @Mapping(source = "email.value", target = "email")
    @Mapping(source = "address.value", target = "address")
    @Mapping(source = "remark.value", target = "remark")
    @Mapping(source = "manager.name", target = "manager")
    CompanyResponseDto companyToCompanyResponseDto(Company company);

    @Mapping(source = "phone", target = "phone.value")
    @Mapping(source = "email", target = "email.value")
    @Mapping(source = "address", target = "address.value")
    @Mapping(source = "remark", target = "remark.value")
    @Mapping(target = "manager", ignore = true)
    Company companyRequestDtoToCompany(CompanyRequestDto companyRequestDto);

    @Mapping(source = "phone", target = "phone.value")
    @Mapping(source = "email", target = "email.value")
    @Mapping(source = "address", target = "address.value")
    @Mapping(source = "remark", target = "remark.value")
    @Mapping(target = "manager", ignore = true)
    Company companyResponseDtoToCompany(CompanyResponseDto companyResponseDto);
}
