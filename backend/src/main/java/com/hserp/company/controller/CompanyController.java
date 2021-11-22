package com.hserp.company.controller;

import com.hserp.company.dto.CompanyRequestDto;
import com.hserp.company.dto.CompanyResponseDto;
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
    public int create(@RequestBody CompanyRequestDto companyRequestDto) throws Exception {
        return companyService.create(companyRequestDto);
    }

    @GetMapping("/{company-id}")
    public CompanyResponseDto read(@PathVariable("company-id") int companyId) {
        return companyService.read(companyId);
    }

    @GetMapping()
    public List<CompanyResponseDto> readAll() {
        return companyService.readAll();
    }

    @PutMapping("/{company-id}")
    public boolean update(@PathVariable("company-id") Integer companyId,
                          @RequestBody CompanyRequestDto companyRequestDto) throws Exception {
        return companyService.update(companyId, companyRequestDto);
    }

    @DeleteMapping("/{company-id}")
    public boolean delete(@PathVariable("company-id") Integer companyId) throws Exception {
        return companyService.delete(companyId);
    }
}
