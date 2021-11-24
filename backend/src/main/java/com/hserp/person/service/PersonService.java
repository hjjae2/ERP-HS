package com.hserp.person.service;

import com.hserp.company.domain.Company;
import com.hserp.company.repository.CompanyRepository;
import com.hserp.global.exception.CustomExceptionMessage;
import com.hserp.person.domain.Person;
import com.hserp.person.dto.PersonDto;
import com.hserp.person.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final CompanyRepository companyRepository;

    @Transactional
    public PersonDto.Create.ResponseDto create(PersonDto.Create.RequestDto requestDto) {
        Company company = null;
        if(requestDto.getCompany() != null && !requestDto.getCompany().isEmpty()) {
            company = companyRepository.findByName(requestDto.getCompany()).orElseThrow(EntityNotFoundException::new);
        }

        Person person = requestDto.toEntity(company);
        return PersonDto.Create.ResponseDto.of(personRepository.save(person));
    }

    public PersonDto.Read.ResponseDto read(int id) {
        Person person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(CustomExceptionMessage.DATA_NOT_FOUND_MESSAGE));
        return PersonDto.Read.ResponseDto.of(person);
    }

    public List<PersonDto.Read.ResponseDto> readAll() {
        // todo : 1. about pageable ?
        List<Person> persons = personRepository.findAll();
        return persons.stream().map(PersonDto.Read.ResponseDto::of).collect(Collectors.toList());
    }

    @Transactional
    public PersonDto.Update.ResponseDto update(int id, PersonDto.Update.RequestDto requestDto) {
        Company company = null;
        if(requestDto.getCompany() != null && !requestDto.getCompany().isEmpty()) {
            company = companyRepository.findByName(requestDto.getCompany()).orElseThrow(EntityNotFoundException::new);
        }
        Person newPerson = requestDto.toEntity(company);

        Person person = personRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        person.changeTo(newPerson);

        return PersonDto.Update.ResponseDto.of(person);
    }

    @Transactional
    public boolean delete(Integer id) {
        personRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        personRepository.deleteById(id);

        return true;
    }
}
