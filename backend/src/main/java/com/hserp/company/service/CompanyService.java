package com.hserp.company.service;

import com.hserp.company.domain.Company;
import com.hserp.company.dto.CompanyDto;
import com.hserp.company.repository.CompanyRepository;
import com.hserp.global.exception.CustomExceptionMessage;
import com.hserp.person.domain.Person;
import com.hserp.person.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CompanyService {

    private final PersonRepository personRepository;
    private final CompanyRepository companyRepository;

    @Transactional
    public CompanyDto.Create.ResponseDto create(CompanyDto.Create.RequestDto requestDto) {
        Person manager = null;
        if(requestDto.getManager() != null && !requestDto.getManager().isEmpty()) {
            manager = personRepository.findByName(requestDto.getManager()).orElseThrow(EntityNotFoundException::new);
        }

        Company company = requestDto.toEntity(manager);
        return CompanyDto.Create.ResponseDto.of(companyRepository.save(company));
    }

    public CompanyDto.Read.ResponseDto read(Integer id) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(CustomExceptionMessage.DATA_NOT_FOUND_MESSAGE));

        return CompanyDto.Read.ResponseDto.of(company);
    }

    public List<CompanyDto.Read.ResponseDto> readAll() {
        // todo : 1. about pageable ?
        List<Company> companies = companyRepository.findAll();
        return companies.stream().map(CompanyDto.Read.ResponseDto::of).collect(Collectors.toList());
    }

    @Transactional
    public CompanyDto.Update.ResponseDto update(Integer id, CompanyDto.Update.RequestDto requestDto) {
        Person manager = null;
        if(requestDto.getManager() != null && !requestDto.getManager().isEmpty()) {
            manager = personRepository.findByName(requestDto.getManager()).orElseThrow(EntityNotFoundException::new);
        }
        Company newCompany = requestDto.toEntity(manager);

        Company company = companyRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        company.changeTo(newCompany);

        return CompanyDto.Update.ResponseDto.of(company);
    }

    @Transactional
    public boolean delete(Integer id) {
        companyRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        companyRepository.deleteById(id);

        return true;
    }
}
