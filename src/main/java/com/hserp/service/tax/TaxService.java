package com.hserp.service.tax;

import com.hserp.dto.tax.TaxRequestDto;
import com.hserp.dto.tax.TaxResponseDto;
import com.hserp.repository.tax.TaxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TaxService {

    private final TaxRepository taxRepository;

    public Integer create(TaxRequestDto taxRequestDto) {
        // todo
        return 1;
    }

    public TaxResponseDto read(Integer id) {
        // todo
        return new TaxResponseDto();
    }

    public Boolean update(Integer id, TaxRequestDto taxRequestDto) {
        // todo
        return true;
    }

    public Boolean delete(Integer id) {
        // todo
        return true;
    }
}
