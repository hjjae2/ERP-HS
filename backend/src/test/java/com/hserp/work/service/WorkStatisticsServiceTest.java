package com.hserp.work.service;

import com.hserp.CommonVariable;
import com.hserp.work.domain.Work;
import com.hserp.work.dto.WorkStatisticsDto;
import com.hserp.work.repository.WorkRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class WorkStatisticsServiceTest {

    @InjectMocks
    WorkStatisticsService workStatisticsService;

    @Mock
    WorkRepository workRepository;

    @DisplayName("월별 요약 :: 2건, 20000원")
    @Test
    void getMonthlySummary() {
        // given
        Work temporaryWork = CommonVariable.getWork();
        List<Work> works = new ArrayList<Work>(){{
            add(temporaryWork);
            add(temporaryWork);
        }};

        Mockito.when(workRepository.findAllByWorkDateBetween(any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(works);

        // when
        WorkStatisticsDto.Month.ResponseDto monthlySummary = workStatisticsService.getMonthlySummary();

        // then
        Assertions.assertThat(monthlySummary.getTotalSummationOfPrices()).isEqualTo(temporaryWork.getPrice() * 2);
        Assertions.assertThat(monthlySummary.getTotalNumberOfWorks()).isEqualTo(2);
        Assertions.assertThat(monthlySummary.getWorks().size()).isEqualTo(2);
    }

    @DisplayName("월별 Price 요약 :: 2021-01, 355원, 2건")
    @Test
    void getMonthlyPriceSummary() {
        // given
        List<String> months = new ArrayList<String>(){{
            add("2021-01");
        }};
        List<Work> works = new ArrayList<Work>(){{
            add(Work.builder().price(100).build());
            add(Work.builder().price(255).build());
        }};

        WorkStatisticsDto.Monthly.RequestDto requestDto = WorkStatisticsDto.Monthly.RequestDto.builder()
                .months(months)
                .build();

        Mockito.when(workRepository.findAllByWorkDateBetween(any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(works);

        // when
        List<WorkStatisticsDto.Monthly.ResponseDto> monthlyPriceSummary = workStatisticsService.getMonthlySummary(requestDto);

        // then
        Assertions.assertThat(monthlyPriceSummary.size()).isEqualTo(1);
        Assertions.assertThat(monthlyPriceSummary.get(0).getMonth()).isEqualTo("2021-01");
        Assertions.assertThat(monthlyPriceSummary.get(0).getTotalSummationOfPrices()).isEqualTo(355);
        Assertions.assertThat(monthlyPriceSummary.get(0).getTotalNumberOfWorks()).isEqualTo(2);
    }
}