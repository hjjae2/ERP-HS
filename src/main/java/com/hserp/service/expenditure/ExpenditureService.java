package com.hserp.service.expenditure;

import com.hserp.dto.expenditure.ExpenditureRequestDto;
import com.hserp.dto.expenditure.ExpenditureResponseDto;
import com.hserp.repository.expenditure.ExpenditureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ExpenditureService {

    private final ExpenditureRepository expenditureRepository;

    public Integer create(ExpenditureRequestDto expenditureRequestDto) {
        // todo
        return 1;
    }

    public ExpenditureResponseDto read(Integer id) {
        // todo
        return new ExpenditureResponseDto();
    }

    public Boolean update(Integer id, ExpenditureRequestDto expenditureRequestDto) {
        // todo
        return true;
    }

    public Boolean delete(Integer id) {
        // todo
        return true;
    }
}
