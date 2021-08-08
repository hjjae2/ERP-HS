package com.hserp.person.mapper;

import com.hserp.CommonVariable;
import com.hserp.entity.company.Company;
import com.hserp.dto.person.PersonRequestDto;
import com.hserp.dto.person.PersonResponseDto;
import com.hserp.entity.person.Person;
import com.hserp.mapper.person.PersonMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class PersonMapperTest {

    @Test
    void personToPersonRequestDto() {
        // given
        Company company = CommonVariable.getCompany();
        Person person = CommonVariable.getPerson(company);

        // when
        PersonRequestDto personRequestDto = PersonMapper.INSTANCE.personToPersonRequestDto(person);

        // then
        System.out.println(personRequestDto);
        Assertions.assertThat(personRequestDto.getName()).isEqualTo(person.getName());
        Assertions.assertThat(personRequestDto.getCompany()).isEqualTo(person.getCompany().getName());
    }

    @Test
    void personToPersonResponseDto() {
        // given
        Company company = CommonVariable.getCompany();
        Person person = CommonVariable.getPerson(company);

        // when
        PersonResponseDto personResponseDto = PersonMapper.INSTANCE.personToPersonResponseDto(person);

        // then
        Assertions.assertThat(personResponseDto.getName()).isEqualTo(person.getName());
        Assertions.assertThat(personResponseDto.getCompany()).isEqualTo(person.getCompany().getName());
    }

    @Test
    void personRequestDtoToPerson() {
        // given
        PersonRequestDto requestDto = new PersonRequestDto(CommonVariable.name, CommonVariable.phone, CommonVariable.email, CommonVariable.address, CommonVariable.remark, CommonVariable.company);

        // when
        Person person = PersonMapper.INSTANCE.personRequestDtoToPerson(requestDto);

        // then
        Assertions.assertThat(person.getName()).isEqualTo(requestDto.getName());
        Assertions.assertThat(person.getCompany().getName()).isEqualTo(requestDto.getCompany());
    }

    @Test
    void personResponseDtoToPerson() {
        // given
        PersonResponseDto responseDto = new PersonResponseDto(null, CommonVariable.name, CommonVariable.phone, CommonVariable.email, CommonVariable.address, CommonVariable.remark, CommonVariable.company, LocalDateTime.now(), LocalDateTime.now());

        // when
        Person person = PersonMapper.INSTANCE.personResponseDtoToPerson(responseDto);

        // then
        Assertions.assertThat(person.getName()).isEqualTo(responseDto.getName());
        Assertions.assertThat(person.getCompany().getName()).isEqualTo(responseDto.getCompany());
    }
}