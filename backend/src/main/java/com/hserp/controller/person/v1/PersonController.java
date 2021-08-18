package com.hserp.controller.person.v1;

import com.hserp.dto.person.PersonRequestDto;
import com.hserp.dto.person.PersonResponseDto;
import com.hserp.service.person.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/people")
@RestController
public class PersonController {

    private final PersonService personService;

    @PostMapping()
    public int create(@RequestBody PersonRequestDto personRequestDto) throws Exception {
        return personService.create(personRequestDto);
    }

    @GetMapping("/{person-id}")
    public PersonResponseDto read(@PathVariable("person-id") Integer personId) {
        return personService.read(personId);
    }

    @GetMapping()
    public List<PersonResponseDto> readAll() {
        return personService.readAll();
    }

    @PutMapping("/{person-id}")
    public boolean update(@PathVariable("person-id") Integer personId,
                          @RequestBody PersonRequestDto personRequestDto) throws Exception {
        return personService.update(personId, personRequestDto);
    }

    @DeleteMapping("/{person-id}")
    public boolean delete(@PathVariable("person-id") Integer personId) throws Exception {
        return personService.delete(personId);
    }
}
