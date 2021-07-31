package com.hserp.controller.work;

import com.hserp.service.work.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/works")
@Controller
public class WorkController {

    private final WorkService workService;

    @GetMapping()
    public String index() {
        return "/views/work";
    }

    @GetMapping("/{work-id}")
    public String index(@PathVariable("work-id") int workId) {
        return "/views/work";
    }

    @PostMapping()
    public String create() {
        return "/views/work";
    }

    @PutMapping()
    public String update() {
        return "/views/work";
    }

    @DeleteMapping
    public String delete() {
        return "/views/work";
    }
}
