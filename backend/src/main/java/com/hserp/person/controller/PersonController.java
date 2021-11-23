package com.hserp.person.controller;

import com.hserp.person.dto.PersonDto;
import com.hserp.person.service.PersonService;
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
    public PersonDto.Create.ResponseDto create(@RequestBody PersonDto.Create.RequestDto requestDto) {
        return personService.create(requestDto);
    }

    @GetMapping("/{person-id}")
    public PersonDto.Read.ResponseDto read(@PathVariable("person-id") int personId) {
        return personService.read(personId);
    }

    @GetMapping()
    public List<PersonDto.Read.ResponseDto> readAll() {
        return personService.readAll();
    }

    @PutMapping("/{person-id}")
    public PersonDto.Update.ResponseDto update(@PathVariable("person-id") Integer personId,
                                               @RequestBody PersonDto.Update.RequestDto requestDto) {
        return personService.update(personId, requestDto);
    }

    @DeleteMapping("/{person-id}")
    public boolean delete(@PathVariable("person-id") Integer personId) {
        return personService.delete(personId);
    }
}
