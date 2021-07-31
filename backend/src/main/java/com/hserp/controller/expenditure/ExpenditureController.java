package com.hserp.controller.expenditure;

import com.hserp.service.expenditure.ExpenditureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/expenditures")
@Controller
public class ExpenditureController {

    private final ExpenditureService expenditureService;

    @GetMapping()
    public String index() {
        return "/views/expenditure";
    }

    @GetMapping("/{expenditure-id}")
    public String index(@PathVariable("expenditure-id") int expenditureId) {
        return "/views/expenditure";
    }

    @PostMapping()
    public String create() {
        return "/views/expenditure";
    }

    @PutMapping()
    public String update() {
        return "/views/expenditure";
    }

    @DeleteMapping
    public String delete() {
        return "/views/expenditure";
    }
}
