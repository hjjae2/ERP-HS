package com.hserp.service.work;

import com.hserp.dto.work.WorkRequestDto;
import com.hserp.dto.work.WorkResponseDto;
import com.hserp.entity.company.Company;
import com.hserp.entity.person.Person;
import com.hserp.entity.work.ExpenditureStatus;
import com.hserp.entity.work.PaymentStatus;
import com.hserp.entity.work.TaxStatus;
import com.hserp.entity.work.Work;
import com.hserp.entity.work.WorkType;
import com.hserp.exception.CustomExceptionMessage;
import com.hserp.mapper.work.WorkMapper;
import com.hserp.repository.company.CompanyRepository;
import com.hserp.repository.person.PersonRepository;
import com.hserp.repository.work.ExpenditureStatusRepository;
import com.hserp.repository.work.PaymentStatusRepository;
import com.hserp.repository.work.TaxStatusRepository;
import com.hserp.repository.work.WorkRepository;
import com.hserp.repository.work.WorkTypeRepository;
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
        Work work = WorkMapper.INSTANCE.workRequestDtoToWork(workRequestDto);

        System.out.println(workRequestDto);
        System.out.println(work);
        Person worker = null;
        Company company = null;

        try {
            if(workRequestDto.getWorker() != null && !workRequestDto.getWorker().isEmpty()) {
                worker = personRepository.findByName(workRequestDto.getWorker()).orElseThrow(EntityNotFoundException::new);
            }
            if(workRequestDto.getCompany() != null && !workRequestDto.getCompany().isEmpty()) {
                company = companyRepository.findByName(workRequestDto.getCompany()).orElseThrow(EntityNotFoundException::new);
            }

            WorkType workType = workTypeRepository.findByName(workRequestDto.getWorkType()).orElseThrow(EntityNotFoundException::new);
            PaymentStatus paymentStatus = paymentStatusRepository.findByName(workRequestDto.getPaymentStatus()).orElseThrow(EntityNotFoundException::new);
            ExpenditureStatus expenditureStatus = expenditureStatusRepository.findByName(workRequestDto.getExpenditureStatus()).orElseThrow(EntityNotFoundException::new);
            TaxStatus taxStatus = taxStatusRepository.findByName(workRequestDto.getTaxStatus()).orElseThrow(EntityNotFoundException::new);

            work.changeWorker(worker);
            work.changeCompany(company);
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
            Company company = null;

            if(workRequestDto.getWorker() != null && !workRequestDto.getWorker().isEmpty()) {
                worker = personRepository.findByName(workRequestDto.getWorker()).orElseThrow(EntityNotFoundException::new);
            }
            if(workRequestDto.getCompany() != null && !workRequestDto.getCompany().isEmpty()) {
                company = companyRepository.findByName(workRequestDto.getCompany()).orElseThrow(EntityNotFoundException::new);
            }

            WorkType workType = workTypeRepository.findByName(workRequestDto.getWorkType()).orElseThrow(EntityNotFoundException::new);
            PaymentStatus paymentStatus = paymentStatusRepository.findByName(workRequestDto.getPaymentStatus()).orElseThrow(EntityNotFoundException::new);
            ExpenditureStatus expenditureStatus = expenditureStatusRepository.findByName(workRequestDto.getExpenditureStatus()).orElseThrow(EntityNotFoundException::new);
            TaxStatus taxStatus = taxStatusRepository.findByName(workRequestDto.getTaxStatus()).orElseThrow(EntityNotFoundException::new);

            newWork.changeWorker(worker);
            newWork.changeCompany(company);
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
