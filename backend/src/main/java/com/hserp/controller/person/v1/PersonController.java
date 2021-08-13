package com.hserp.controller.person.v1;

import com.hserp.annotation.Log4RestController;
import com.hserp.dto.person.PersonRequestDto;
import com.hserp.service.person.PersonService;
import com.hserp.vo.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/people")
@RestController
public class PersonController {

    private final PersonService personService;

    @PostMapping()
    public ApiResponse create(@RequestBody PersonRequestDto personRequestDto) throws Exception {
        Integer personId = personService.create(personRequestDto);
        return ApiResponse.success(personId);
    }

    @GetMapping("/{person-id}")
    public ApiResponse read(@PathVariable("person-id") Integer personId) {
        return ApiResponse.success(personService.read(personId));
    }

    @GetMapping()
    public ApiResponse readAll() {
        return ApiResponse.success(personService.readAll());
    }

    @PutMapping("/{person-id}")
    public ApiResponse update(@PathVariable("person-id") Integer personId,
                              @RequestBody PersonRequestDto personRequestDto) throws Exception {
        return ApiResponse.success(personService.update(personId, personRequestDto));
    }

    @DeleteMapping("/{person-id}")
    public ApiResponse delete(@PathVariable("person-id") Integer personId) throws Exception {
        return ApiResponse.success(personService.delete(personId));
    }
}
