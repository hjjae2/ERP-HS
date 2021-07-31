package com.hserp.controller.payment;

import com.hserp.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/payments")
@Controller
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping()
    public String index() {
        return "/views/payment";
    }

    @GetMapping("/{payment-id}")
    public String index(@PathVariable("payment-id") int paymentId) {
        return "/views/payment";
    }

    @PostMapping()
    public String create() {
        return "/views/payment";
    }

    @PutMapping()
    public String update() {
        return "/views/payment";
    }

    @DeleteMapping
    public String delete() {
        return "/views/payment";
    }
}
