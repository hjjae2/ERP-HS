package com.hserp.controller.person;

import com.hserp.service.person.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/persons")
@Controller
public class PersonController {

    private final PersonService personService;

    @GetMapping()
    public String index() {
        return "/views/person";
    }

    @GetMapping("/{person-id}")
    public String index(@PathVariable("person-id") int personId) {
        return "/views/person";
    }

    @PostMapping()
    public String create() {
        return "/views/person";
    }

    @PutMapping()
    public String update() {
        return "/views/person";
    }

    @DeleteMapping
    public String delete() {
        return "/views/person";
    }
}
