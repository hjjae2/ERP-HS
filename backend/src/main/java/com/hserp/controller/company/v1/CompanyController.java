package com.hserp.controller.company.v1;

import com.hserp.annotation.Log4RestController;
import com.hserp.dto.company.CompanyRequestDto;
import com.hserp.service.company.CompanyService;
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
@RequestMapping("/api/v1/companies")
@RestController
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping()
    public ApiResponse create(CompanyRequestDto companyRequestDto) throws Exception {
        Integer companyId = companyService.create(companyRequestDto);
        return ApiResponse.success(companyId);
    }

    @GetMapping("/{company-id}")
    public ApiResponse read(@PathVariable("company-id") int companyId) {
        return ApiResponse.success(companyService.read(companyId));
    }

    @GetMapping()
    public ApiResponse readAll() {
        return ApiResponse.success(companyService.readAll());
    }

    @PutMapping("/{company-id}")
    public ApiResponse update(@PathVariable("company-id") Integer companyId,
                              @RequestBody CompanyRequestDto companyRequestDto) throws Exception {
        return ApiResponse.success(companyService.update(companyId, companyRequestDto));
    }

    @DeleteMapping("/{company-id}")
    public ApiResponse delete(@PathVariable("company-id") Integer companyId) throws Exception {
        return ApiResponse.success(companyService.delete(companyId));
    }
}
