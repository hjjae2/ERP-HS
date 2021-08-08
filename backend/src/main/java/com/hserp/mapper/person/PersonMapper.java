package com.hserp.mapper.person;

import com.hserp.dto.person.PersonRequestDto;
import com.hserp.dto.person.PersonResponseDto;
import com.hserp.entity.person.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(source = "phone.value", target = "phone")
    @Mapping(source = "email.value", target = "email")
    @Mapping(source = "address.value", target = "address")
    @Mapping(source = "remark.value", target = "remark")
    @Mapping(source = "manager.name", target = "manager")
    PersonRequestDto personToPersonRequestDto(Person person);

    @Mapping(source = "phone.value", target = "phone")
    @Mapping(source = "email.value", target = "email")
    @Mapping(source = "address.value", target = "address")
    @Mapping(source = "remark.value", target = "remark")
    @Mapping(source = "manager.name", target = "manager")
    PersonResponseDto personToPersonResponseDto(Person person);

    @Mapping(source = "phone", target = "phone.value")
    @Mapping(source = "email", target = "email.value")
    @Mapping(source = "address", target = "address.value")
    @Mapping(source = "remark", target = "remark.value")
    @Mapping(source = "manager", target = "manager.name")
    Person personRequestDtoToPerson(PersonRequestDto personRequestDto);

    @Mapping(source = "phone", target = "phone.value")
    @Mapping(source = "email", target = "email.value")
    @Mapping(source = "address", target = "address.value")
    @Mapping(source = "remark", target = "remark.value")
    @Mapping(source = "manager", target = "manager.name")
    Person personResponseDtoToPerson(PersonResponseDto personResponseDto);
}
