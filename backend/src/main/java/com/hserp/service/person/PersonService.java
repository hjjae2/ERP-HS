package com.hserp.service.person;

import com.hserp.dto.person.PersonRequestDto;
import com.hserp.dto.person.PersonResponseDto;
import com.hserp.repository.person.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PersonService {

    private final PersonRepository personRepository;

    public Integer create(PersonRequestDto personRequestDto) {
        // todo
        return 1;
    }

    public PersonResponseDto read(Integer id) {
        // todo
        return new PersonResponseDto();
    }

    public Boolean update(Integer id, PersonRequestDto personRequestDto) {
        // todo
        return true;
    }

    public Boolean delete(Integer id) {
        // todo
        return true;
    }
}
