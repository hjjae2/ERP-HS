package com.hserp.work.controller;

import com.hserp.global.annotation.Log4RestController;
import com.hserp.work.dto.WorkDto;
import com.hserp.work.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/works")
@RestController
public class WorkController {

    private final WorkService workService;

    @PostMapping()
    public WorkDto.Create.ResponseDto create(@RequestBody WorkDto.Create.RequestDto requestDto) {
        return workService.create(requestDto);
    }

    @GetMapping("/{work-id}")
    public WorkDto.Read.ResponseDto read(@PathVariable("work-id") int workId) {
        return workService.read(workId);
    }

    @GetMapping()
    public List<WorkDto.Read.ResponseDto> readAll() {
        return workService.readAll();
    }

    @PutMapping("/{work-id}")
    public WorkDto.Update.ResponseDto update(@PathVariable("work-id") int workId,
                                             @RequestBody WorkDto.Update.RequestDto requestDto) {
        return workService.update(workId, requestDto);
    }

    @DeleteMapping("/{work-id}")
    public boolean delete(@PathVariable("work-id") int workId) {
        return workService.delete(workId);
    }
}
