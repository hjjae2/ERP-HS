package com.hserp.company.controller;

import com.hserp.company.dto.CompanyDto;
import com.hserp.company.service.CompanyService;
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
@RequestMapping("/api/v1/companies")
@RestController
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping()
    public CompanyDto.Create.ResponseDto create(@RequestBody CompanyDto.Create.RequestDto companyDto) {
        return companyService.create(companyDto);
    }

    @GetMapping("/{company-id}")
    public CompanyDto.Read.ResponseDto read(@PathVariable("company-id") int companyId) {
        return companyService.read(companyId);
    }

    @GetMapping()
    public List<CompanyDto.Read.ResponseDto> readAll() {
        return companyService.readAll();
    }

    @PutMapping("/{company-id}")
    public CompanyDto.Update.ResponseDto update(@PathVariable("company-id") Integer companyId,
                                                @RequestBody CompanyDto.Update.RequestDto requestDto) {
        return companyService.update(companyId, requestDto);
    }

    @DeleteMapping("/{company-id}")
    public boolean delete(@PathVariable("company-id") Integer companyId) {
        return companyService.delete(companyId);
    }
}
