package com.hserp.controller.company;

import com.hserp.dto.company.CompanyRequestDto;
import com.hserp.dto.company.CompanyResponseDto;
import com.hserp.service.company.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/companies")
@Controller
public class CompanyControllerV1 {

    private final CompanyService companyService;

    @PostMapping()
    public Integer create(CompanyRequestDto companyRequestDto) throws Exception {
        Integer companyId = companyService.create(companyRequestDto);

        return companyId;
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
