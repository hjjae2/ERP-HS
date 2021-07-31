package com.hserp.service.company;

import com.hserp.dto.company.CompanyRequestDto;
import com.hserp.dto.company.CompanyResponseDto;
import com.hserp.repository.company.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Integer create(CompanyRequestDto companyRequestDto) {
        // todo
        return 1;
    }

    public CompanyResponseDto read(Integer id) {
        // todo
        return new CompanyResponseDto();
    }

    public Boolean update(Integer id, CompanyRequestDto companyRequestDto) {
        // todo
        return true;
    }

    public Boolean delete(Integer id) {
        // todo
        return true;
    }
}
