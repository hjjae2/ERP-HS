package com.hserp.service.company;

import com.hserp.dto.company.CompanyRequestDto;
import com.hserp.dto.company.CompanyResponseDto;
import com.hserp.entity.company.Company;
import com.hserp.exception.CustomExceptionMessage;
import com.hserp.mapper.company.CompanyMapper;
import com.hserp.mapper.person.PersonMapper;
import com.hserp.repository.company.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Integer create(CompanyRequestDto companyRequestDto) throws Exception {
        Company company = CompanyMapper.INSTANCE.companyRequestDtoToCompany(companyRequestDto);

        try {
            return companyRepository.save(company).getId();
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(CustomExceptionMessage.BAD_REQUEST_MESSAGE);
        } catch (Exception e) {
            throw new Exception(CustomExceptionMessage.INTERNAL_EXCEPTION_MESSAGE);
        }
    }

    public CompanyResponseDto read(Integer id) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(CustomExceptionMessage.DATA_NOT_FOUND_MESSAGE));

        return CompanyMapper.INSTANCE.companyToCompanyResponseDto(company);
    }

    public List<CompanyResponseDto> readAll() {
        // todo : 1. about pageable ?
        List<Company> companies = companyRepository.findAll();
        return companies.stream().map(CompanyMapper.INSTANCE::companyToCompanyResponseDto).collect(Collectors.toList());
    }

    public boolean update(Integer id, CompanyRequestDto companyRequestDto) throws Exception {
        try {
            Company company = companyRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            Company newCompany = CompanyMapper.INSTANCE.companyRequestDtoToCompany(companyRequestDto);

            if(!company.equals(newCompany)) {
                company.changeTo(newCompany);
            }
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(CustomExceptionMessage.DATA_NOT_FOUND_MESSAGE);
        } catch (Exception e) {
            throw new Exception(CustomExceptionMessage.INTERNAL_EXCEPTION_MESSAGE);
        }

        return true;
    }

    public boolean delete(Integer id) throws Exception {
        try {
            companyRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(CustomExceptionMessage.INTERNAL_EXCEPTION_MESSAGE);
        }

        return true;
    }
}
