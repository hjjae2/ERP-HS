package com.hserp.work.service;

import com.hserp.work.dto.WorkRequestDto;
import com.hserp.work.dto.WorkResponseDto;
import com.hserp.company.domain.Company;
import com.hserp.person.domain.Person;
import com.hserp.work.domain.ExpenditureStatus;
import com.hserp.work.domain.PaymentStatus;
import com.hserp.work.domain.TaxStatus;
import com.hserp.work.domain.Work;
import com.hserp.work.domain.WorkType;
import com.hserp.global.exception.CustomExceptionMessage;
import com.hserp.work.WorkMapper;
import com.hserp.company.repository.CompanyRepository;
import com.hserp.person.repository.PersonRepository;
import com.hserp.work.repository.ExpenditureStatusRepository;
import com.hserp.work.repository.PaymentStatusRepository;
import com.hserp.work.repository.TaxStatusRepository;
import com.hserp.work.repository.WorkRepository;
import com.hserp.work.repository.WorkTypeRepository;
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
public class WorkService {

    private final WorkRepository workRepository;
    private final PersonRepository personRepository;
    private final CompanyRepository companyRepository;
    private final WorkTypeRepository workTypeRepository;
    private final PaymentStatusRepository paymentStatusRepository;
    private final ExpenditureStatusRepository expenditureStatusRepository;
    private final TaxStatusRepository taxStatusRepository;

    @Transactional
    public int create(WorkRequestDto workRequestDto) throws Exception {
        try {
            Work work = WorkMapper.INSTANCE.workRequestDtoToWork(workRequestDto);

            Person worker = null;
            Person dispatcher = null;
            Company company = null;
            Company customer = null;

            if(workRequestDto.getWorker() != null && !workRequestDto.getWorker().isEmpty()) {
                worker = personRepository.findByName(workRequestDto.getWorker()).orElseThrow(EntityNotFoundException::new);
            }
            if(workRequestDto.getDispatcher() != null && !workRequestDto.getDispatcher().isEmpty()) {
                dispatcher = personRepository.findByName(workRequestDto.getDispatcher()).orElseThrow(EntityNotFoundException::new);
            }
            if(workRequestDto.getCompany() != null && !workRequestDto.getCompany().isEmpty()) {
                company = companyRepository.findByName(workRequestDto.getCompany()).orElseThrow(EntityNotFoundException::new);
            }
            if(workRequestDto.getCustomer() != null && !workRequestDto.getCustomer().isEmpty()) {
                customer = companyRepository.findByName(workRequestDto.getCustomer()).orElseThrow(EntityNotFoundException::new);
            }

            WorkType workType = workTypeRepository.findByName(workRequestDto.getWorkType()).orElseThrow(EntityNotFoundException::new);
            PaymentStatus paymentStatus = paymentStatusRepository.findByName(workRequestDto.getPaymentStatus()).orElseThrow(EntityNotFoundException::new);
            ExpenditureStatus expenditureStatus = expenditureStatusRepository.findByName(workRequestDto.getExpenditureStatus()).orElseThrow(EntityNotFoundException::new);
            TaxStatus taxStatus = taxStatusRepository.findByName(workRequestDto.getTaxStatus()).orElseThrow(EntityNotFoundException::new);

            work.changeWorker(worker);
            work.changeCompany(company);
            work.changeCustomer(customer);
            work.changeDispatcher(dispatcher);
            work.changeWorkType(workType);
            work.changePaymentStatus(paymentStatus);
            work.changeExpenditureStatus(expenditureStatus);
            work.changeTaxStatus(taxStatus);

            return workRepository.save(work).getId();
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(CustomExceptionMessage.DATA_NOT_FOUND_MESSAGE);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(CustomExceptionMessage.BAD_REQUEST_MESSAGE(e.getMostSpecificCause().getMessage()));
        } catch (Exception e) {
            throw new Exception(CustomExceptionMessage.INTERNAL_EXCEPTION_MESSAGE);
        }
    }

    public WorkResponseDto read(Integer id) {
        Work work = workRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(CustomExceptionMessage.DATA_NOT_FOUND_MESSAGE));

        return WorkMapper.INSTANCE.workToWorkResponseDto(work);
    }

    public List<WorkResponseDto> readAll() {
        // todo : 1. about pageable ?
        List<Work> works = workRepository.findAll();
        return works.stream().map(WorkMapper.INSTANCE::workToWorkResponseDto).collect(Collectors.toList());
    }

    @Transactional
    public boolean update(Integer id, WorkRequestDto workRequestDto) throws Exception {
        try {
            Work work = workRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            Work newWork = WorkMapper.INSTANCE.workRequestDtoToWork(workRequestDto);
            Person worker = null;
            Person dispatcher = null;
            Company company = null;
            Company customer = null;

            if(workRequestDto.getWorker() != null && !workRequestDto.getWorker().isEmpty()) {
                worker = personRepository.findByName(workRequestDto.getWorker()).orElseThrow(EntityNotFoundException::new);
            }
            if(workRequestDto.getDispatcher() != null && !workRequestDto.getDispatcher().isEmpty()) {
                dispatcher = personRepository.findByName(workRequestDto.getDispatcher()).orElseThrow(EntityNotFoundException::new);
            }
            if(workRequestDto.getCompany() != null && !workRequestDto.getCompany().isEmpty()) {
                company = companyRepository.findByName(workRequestDto.getCompany()).orElseThrow(EntityNotFoundException::new);
            }
            if(workRequestDto.getCustomer() != null && !workRequestDto.getCustomer().isEmpty()) {
                customer = companyRepository.findByName(workRequestDto.getCustomer()).orElseThrow(EntityNotFoundException::new);
            }

            WorkType workType = workTypeRepository.findByName(workRequestDto.getWorkType()).orElseThrow(EntityNotFoundException::new);
            PaymentStatus paymentStatus = paymentStatusRepository.findByName(workRequestDto.getPaymentStatus()).orElseThrow(EntityNotFoundException::new);
            ExpenditureStatus expenditureStatus = expenditureStatusRepository.findByName(workRequestDto.getExpenditureStatus()).orElseThrow(EntityNotFoundException::new);
            TaxStatus taxStatus = taxStatusRepository.findByName(workRequestDto.getTaxStatus()).orElseThrow(EntityNotFoundException::new);

            newWork.changeWorker(worker);
            newWork.changeCompany(company);
            newWork.changeCustomer(customer);
            newWork.changeDispatcher(dispatcher);
            newWork.changeWorkType(workType);
            newWork.changePaymentStatus(paymentStatus);
            newWork.changeExpenditureStatus(expenditureStatus);
            newWork.changeTaxStatus(taxStatus);

            if(!work.equals(newWork)) {
                work.changeTo(newWork);
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
            workRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            workRepository.deleteById(id);
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
