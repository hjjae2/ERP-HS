package com.hserp.work.service;

import com.hserp.work.domain.Work;
import com.hserp.work.dto.WorkDto;
import com.hserp.work.dto.WorkStatisticsDto;
import com.hserp.work.repository.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class WorkStatisticsService {

    private final WorkRepository workRepository;

    public WorkStatisticsDto.Month.ResponseDto getMonthlySummary() {
        LocalDate now = LocalDate.now();
        YearMonth yearMonth = YearMonth.of(now.getYear(), now.getMonth());
        List<Work> thisMonthWorks = workRepository.findAllByWorkDateBetween(yearMonth.atDay(1), yearMonth.atEndOfMonth());

        Integer priceSummation = thisMonthWorks.stream().map(Work::getPrice).reduce(Integer::sum).orElse(0);
        List<WorkDto.Read.ResponseDto> works = thisMonthWorks.stream().map(WorkDto.Read.ResponseDto::of).collect(Collectors.toList());

        return WorkStatisticsDto.Month.ResponseDto.of(works, priceSummation);
    }

    public List<WorkStatisticsDto.Monthly.ResponseDto> getMonthlySummary(WorkStatisticsDto.Monthly.RequestDto requestDto) {
        List<WorkStatisticsDto.Monthly.ResponseDto> responseDtos = new LinkedList<>();

        List<LocalDate> months = requestDto.getMonths().stream()
                .distinct()
                .map(requestDto::stringToLocalDate)
                .collect(Collectors.toList());

        for(LocalDate month : months) {
            YearMonth yearMonth = YearMonth.of(month.getYear(), month.getMonth());
            List<Work> monthlyWorks = workRepository.findAllByWorkDateBetween(yearMonth.atDay(1), yearMonth.atEndOfMonth());

            Integer monthlyPriceSummation = monthlyWorks.stream().map(Work::getPrice).reduce(Integer::sum).orElse(0);
            responseDtos.add(WorkStatisticsDto.Monthly.ResponseDto.of(month, monthlyPriceSummation, monthlyWorks.size()));
        }

        return responseDtos;
    }
}
