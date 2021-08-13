package com.hserp.service.person;

import com.hserp.dto.person.PersonRequestDto;
import com.hserp.dto.person.PersonResponseDto;
import com.hserp.entity.company.Company;
import com.hserp.entity.person.Person;
import com.hserp.exception.CustomExceptionMessage;
import com.hserp.mapper.person.PersonMapper;
import com.hserp.repository.company.CompanyRepository;
import com.hserp.repository.person.PersonRepository;
import com.hserp.service.company.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
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
    public Integer create(PersonRequestDto personRequestDto) throws Exception {
        Person person = PersonMapper.INSTANCE.personRequestDtoToPerson(personRequestDto);
        Company company = null;

        try {
            if(personRequestDto.getCompany() != null) {
                company = companyRepository.findByName(personRequestDto.getCompany()).orElseThrow(EntityNotFoundException::new);
            }
            person.changeCompany(company);

            System.out.println(person);

            return personRepository.save(person).getId();
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(CustomExceptionMessage.DATA_NOT_FOUND_MESSAGE);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(CustomExceptionMessage.BAD_REQUEST_MESSAGE);
        } catch (Exception e) {
            throw new Exception(CustomExceptionMessage.INTERNAL_EXCEPTION_MESSAGE);
        }
    }

    public PersonResponseDto read(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(CustomExceptionMessage.DATA_NOT_FOUND_MESSAGE));

        return PersonMapper.INSTANCE.personToPersonResponseDto(person);
    }

    public List<PersonResponseDto> readAll() {
        // todo : 1. about pageable ?
        List<Person> persons = personRepository.findAll();
        return persons.stream().map(PersonMapper.INSTANCE::personToPersonResponseDto).collect(Collectors.toList());
    }

    @Transactional
    public boolean update(Integer id, PersonRequestDto personRequestDto) throws Exception {
        try {
            Person person = personRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            Person newPerson = PersonMapper.INSTANCE.personRequestDtoToPerson(personRequestDto);

            if(!person.equals(newPerson)) {
                person.changeTo(newPerson);
            }
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(CustomExceptionMessage.DATA_NOT_FOUND_MESSAGE);
        } catch (Exception e) {
            throw new Exception(CustomExceptionMessage.INTERNAL_EXCEPTION_MESSAGE);
        }

        return true;
    }

    @Transactional
    public boolean delete(Integer id) throws Exception {
        try {
            personRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(CustomExceptionMessage.INTERNAL_EXCEPTION_MESSAGE);
        }

        return true;
    }
}
