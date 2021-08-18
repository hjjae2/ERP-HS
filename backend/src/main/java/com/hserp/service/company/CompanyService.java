package com.hserp.service.company;

import com.hserp.dto.company.CompanyRequestDto;
import com.hserp.dto.company.CompanyResponseDto;
import com.hserp.entity.company.Company;
import com.hserp.entity.person.Person;
import com.hserp.exception.CustomExceptionMessage;
import com.hserp.mapper.company.CompanyMapper;
import com.hserp.repository.company.CompanyRepository;
import com.hserp.repository.person.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
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
    public int create(CompanyRequestDto companyRequestDto) throws Exception {
        Company company = CompanyMapper.INSTANCE.companyRequestDtoToCompany(companyRequestDto);
        Person manager = null;

        try {
            if(companyRequestDto.getManager() != null && !companyRequestDto.getManager().isEmpty()) {
                manager = personRepository.findByName(companyRequestDto.getManager()).orElseThrow(EntityNotFoundException::new);
            }
            company.changeManager(manager);

            return companyRepository.save(company).getId();
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(CustomExceptionMessage.BAD_REQUEST_MESSAGE(e.getMostSpecificCause().getMessage()));
        } catch (Exception e) {
            throw new Exception(CustomExceptionMessage.INTERNAL_EXCEPTION_MESSAGE(e.getMessage()));
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

    @Transactional
    public boolean update(Integer id, CompanyRequestDto companyRequestDto) throws Exception {
        try {
            Company company = companyRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            Company newCompany = CompanyMapper.INSTANCE.companyRequestDtoToCompany(companyRequestDto);
            Person manager = null;

            if(companyRequestDto.getManager() != null && !companyRequestDto.getManager().isEmpty()) {
                manager = personRepository.findByName(companyRequestDto.getManager()).orElseThrow(EntityNotFoundException::new);
            }
            newCompany.changeManager(manager);

            if(!company.equals(newCompany)) {
                company.changeTo(newCompany);
            }
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(CustomExceptionMessage.DATA_NOT_FOUND_MESSAGE);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(CustomExceptionMessage.BAD_REQUEST_MESSAGE(e.getMostSpecificCause().getMessage()));
        } catch (Exception e) {
            throw new Exception(CustomExceptionMessage.INTERNAL_EXCEPTION_MESSAGE);
        }

        return true;
    }

    @Transactional
    public boolean delete(Integer id) throws Exception {
        try {
            companyRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            companyRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(CustomExceptionMessage.DATA_NOT_FOUND_MESSAGE);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(CustomExceptionMessage.BAD_REQUEST_MESSAGE(e.getMostSpecificCause().getMessage()));
        } catch (Exception e) {
            throw new Exception(CustomExceptionMessage.INTERNAL_EXCEPTION_MESSAGE);
        }

        return true;
    }
}
