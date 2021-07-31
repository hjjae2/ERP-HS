package com.hserp.controller.company;

import com.hserp.dto.company.CompanyRequestDto;
import com.hserp.service.company.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/companies")
@Controller
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping()
    public String index() {
        return "/views/company";
    }

    @GetMapping("/{company-id}")
    public String index(@PathVariable("company-id") int companyId) {
        return "/views/company";
    }

    @PostMapping()
    public String create(CompanyRequestDto companyRequestDto) {
        companyService.create(companyRequestDto);
        return "/views/company";
    }

    @PutMapping()
    public String update() {
        return "/views/company";
    }

    @DeleteMapping
    public String delete() {
        return "/views/company";
    }
}
