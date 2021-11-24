package com.hserp.work.service;

import com.hserp.company.domain.Company;
import com.hserp.company.repository.CompanyRepository;
import com.hserp.global.exception.CustomExceptionMessage;
import com.hserp.person.domain.Person;
import com.hserp.person.repository.PersonRepository;
import com.hserp.work.domain.Work;
import com.hserp.work.dto.WorkDto;
import com.hserp.work.repository.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class WorkService {

    private final WorkRepository workRepository;
    private final PersonRepository personRepository;
    private final CompanyRepository companyRepository;

    @Transactional
    public WorkDto.Create.ResponseDto create(WorkDto.Create.RequestDto requestDto) {
        Person worker = null;
        Person dispatcher = null;
        Company company = null;
        Company customer = null;
        if(requestDto.getWorker() != null && !requestDto.getWorker().isEmpty()) {
            worker = personRepository.findByName(requestDto.getWorker()).orElseThrow(EntityNotFoundException::new);
        }
        if(requestDto.getDispatcher() != null && !requestDto.getDispatcher().isEmpty()) {
            dispatcher = personRepository.findByName(requestDto.getDispatcher()).orElseThrow(EntityNotFoundException::new);
        }
        if(requestDto.getCompany() != null && !requestDto.getCompany().isEmpty()) {
            company = companyRepository.findByName(requestDto.getCompany()).orElseThrow(EntityNotFoundException::new);
        }
        if(requestDto.getCustomer() != null && !requestDto.getCustomer().isEmpty()) {
            customer = companyRepository.findByName(requestDto.getCustomer()).orElseThrow(EntityNotFoundException::new);
        }

        Work work = requestDto.toEntity(worker, dispatcher, company, customer);
        return WorkDto.Create.ResponseDto.of(workRepository.save(work));
    }

    public WorkDto.Read.ResponseDto read(int id) {
        Work work = workRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(CustomExceptionMessage.DATA_NOT_FOUND_MESSAGE));

        return WorkDto.Read.ResponseDto.of(work);
    }

    public List<WorkDto.Read.ResponseDto> readAll() {
        // todo : 1. about pageable ?
        List<Work> works = workRepository.findAll();
        return works.stream().map(WorkDto.Read.ResponseDto::of).collect(Collectors.toList());
    }

    @Transactional
    public WorkDto.Update.ResponseDto update(Integer id, WorkDto.Update.RequestDto workDto) {
        Person worker = null;
        Person dispatcher = null;
        Company company = null;
        Company customer = null;
        if(workDto.getWorker() != null && !workDto.getWorker().isEmpty()) {
            worker = personRepository.findByName(workDto.getWorker()).orElseThrow(EntityNotFoundException::new);
        }
        if(workDto.getDispatcher() != null && !workDto.getDispatcher().isEmpty()) {
            dispatcher = personRepository.findByName(workDto.getDispatcher()).orElseThrow(EntityNotFoundException::new);
        }
        if(workDto.getCompany() != null && !workDto.getCompany().isEmpty()) {
            company = companyRepository.findByName(workDto.getCompany()).orElseThrow(EntityNotFoundException::new);
        }
        if(workDto.getCustomer() != null && !workDto.getCustomer().isEmpty()) {
            customer = companyRepository.findByName(workDto.getCustomer()).orElseThrow(EntityNotFoundException::new);
        }
        Work newWork = workDto.toEntity(worker, dispatcher, company, customer);

        Work work = workRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        work.changeTo(newWork);

        return WorkDto.Update.ResponseDto.of(work);
    }

    @Transactional
    public boolean delete(Integer id) {
        workRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        workRepository.deleteById(id);

        return true;
    }
}
