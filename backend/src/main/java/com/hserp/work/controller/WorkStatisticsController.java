package com.hserp.work.controller;

import com.hserp.global.annotation.Log4RestController;
import com.hserp.work.dto.WorkStatisticsDto;
import com.hserp.work.service.WorkStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/works/statistics")
@RestController
public class WorkStatisticsController {

    private final WorkStatisticsService workStatisticsService;

    @GetMapping("/monthly/this")
    public WorkStatisticsDto.Month.ResponseDto getMonthlySummary() {
        return workStatisticsService.getMonthlySummary();
    }

    @GetMapping("/monthly")
    public List<WorkStatisticsDto.Monthly.ResponseDto> getMonthlySummary(@RequestBody WorkStatisticsDto.Monthly.RequestDto requestDto) {
        return workStatisticsService.getMonthlySummary(requestDto);
    }
}
