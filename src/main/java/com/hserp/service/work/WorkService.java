package com.hserp.service.work;

import com.hserp.dto.work.WorkRequestDto;
import com.hserp.dto.work.WorkResponseDto;
import com.hserp.entity.work.Work;
import com.hserp.repository.work.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class WorkService {

    private final WorkRepository workRepository;

    public Integer create(WorkRequestDto workRequestDto) {
        // todo
        return 1;
    }

    public WorkResponseDto read(Integer id) {
        // todo
        return new WorkResponseDto();
    }

    public List<WorkResponseDto> readAll() {
        List<WorkResponseDto> results = new ArrayList<>();
        List<Work> works = workRepository.findAll();

        works.forEach(work -> results.add(WorkResponseDto.of(work)));

        return results;
    }

    public Boolean update(Integer id, WorkRequestDto workRequestDto) {
        // todo
        return true;
    }

    public Boolean delete(Integer id) {
        // todo
        return true;
    }
}
