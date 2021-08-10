package com.hserp.controller.work.v1;

import com.hserp.dto.work.WorkRequestDto;
import com.hserp.dto.work.WorkResponseDto;
import com.hserp.service.work.WorkService;
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

@RequiredArgsConstructor
@RequestMapping("/api/v1/works")
@RestController
public class WorkController {

    private final WorkService workService;

    @PostMapping()
    public Integer create(@RequestBody WorkRequestDto workRequestDto) throws Exception {
        Integer workId = workService.create(workRequestDto);
        return workId;
    }

    @GetMapping("/{work-id}")
    public WorkResponseDto read(@PathVariable("work-id") int workId) {
        return workService.read(workId);
    }

    @GetMapping()
    public List<WorkResponseDto> readAll(@PathVariable("work-id") int workId) {
        return workService.readAll();
    }

    @PutMapping("/{work-id}")
    public boolean update(@PathVariable("work-id") int workId, @RequestBody WorkRequestDto workRequestDto) throws Exception {
        return workService.update(workId, workRequestDto);
    }

    @DeleteMapping("/{work-id}")
    public boolean delete(@PathVariable int workId) throws Exception {
        return workService.delete(workId);
    }
}
