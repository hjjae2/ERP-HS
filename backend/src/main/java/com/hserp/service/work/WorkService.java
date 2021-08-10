package com.hserp.service.work;

import com.hserp.dto.work.WorkRequestDto;
import com.hserp.dto.work.WorkResponseDto;
import com.hserp.entity.work.Work;
import com.hserp.exception.CustomExceptionMessage;
import com.hserp.mapper.work.WorkMapper;
import com.hserp.repository.work.WorkRepository;
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

    @Transactional
    public Integer create(WorkRequestDto workRequestDto) throws Exception {
        Work work = WorkMapper.INSTANCE.workRequestDtoToWork(workRequestDto);

        try {
            return workRepository.save(work).getId();
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(CustomExceptionMessage.BAD_REQUEST_MESSAGE);
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

            if(!work.equals(newWork)) {
                work.changeTo(newWork);
            }
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(CustomExceptionMessage.DATA_NOT_FOUND_MESSAGE);
        } catch (Exception e) {
            throw new Exception(CustomExceptionMessage.INTERNAL_EXCEPTION_MESSAGE);
        }

        return true;
    }

    @Transactional
    public boolean delete(Integer id) throws Exception {
        try {
            workRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(CustomExceptionMessage.INTERNAL_EXCEPTION_MESSAGE);
        }

        return true;
    }
}
