package com.hserp.work.controller;

import com.hserp.global.annotation.Log4RestController;
import com.hserp.work.dto.WorkStatisticsDto;
import com.hserp.work.service.WorkStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/works/statistics")
@RestController
public class WorkStatisticsController {

    private final WorkStatisticsService workStatisticsService;

    @GetMapping("/monthly/this")
    public WorkStatisticsDto.Month.ResponseDto getThisMonthSummary() {
        return workStatisticsService.getMonthlySummary();
    }

    @GetMapping("/monthly")
    public List<WorkStatisticsDto.Monthly.ResponseDto> getMonthlySummary(@RequestParam(value = "months[]") String[] months) {
        return workStatisticsService.getMonthlySummary(WorkStatisticsDto.Monthly.RequestDto.of(months));
    }
}
