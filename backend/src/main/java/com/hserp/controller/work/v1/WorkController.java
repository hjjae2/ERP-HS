package com.hserp.controller.work.v1;

import com.hserp.annotation.Log4RestController;
import com.hserp.dto.work.WorkRequestDto;
import com.hserp.service.work.WorkService;
import com.hserp.vo.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/works")
@RestController
public class WorkController {

    private final WorkService workService;

    @PostMapping()
    public ApiResponse create(@RequestBody WorkRequestDto workRequestDto) throws Exception {
        Integer workId = workService.create(workRequestDto);
        return ApiResponse.success(workId);
    }

    @GetMapping("/{work-id}")
    public ApiResponse read(@PathVariable("work-id") int workId) {
        return ApiResponse.success(workService.read(workId));
    }

    @GetMapping()
    public ApiResponse readAll() {
        return ApiResponse.success(workService.readAll());
    }

    @PutMapping("/{work-id}")
    public ApiResponse update(@PathVariable("work-id") int workId, @RequestBody WorkRequestDto workRequestDto) throws Exception {
        return ApiResponse.success(workService.update(workId, workRequestDto));
    }

    @DeleteMapping("/{work-id}")
    public ApiResponse delete(@PathVariable int workId) throws Exception {
        return ApiResponse.success(workService.delete(workId));
    }
}
