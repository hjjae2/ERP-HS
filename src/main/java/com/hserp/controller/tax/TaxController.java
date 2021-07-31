package com.hserp.controller.tax;

import com.hserp.service.tax.TaxService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/taxes")
@Controller
public class TaxController {

    private final TaxService taxService;

    @GetMapping()
    public String index() {
        return "/views/tax";
    }

    @GetMapping("/{tax-id}")
    public String index(@PathVariable("tax-id") int taxId) {
        return "/views/tax";
    }

    @PostMapping()
    public String create() {
        return "/views/tax";
    }

    @PutMapping()
    public String update() {
        return "/views/tax";
    }

    @DeleteMapping
    public String delete() {
        return "/views/tax";
    }
}
