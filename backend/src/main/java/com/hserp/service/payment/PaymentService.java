package com.hserp.service.payment;

import com.hserp.dto.payment.PaymentRequestDto;
import com.hserp.dto.payment.PaymentResponseDto;
import com.hserp.repository.payment.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public Integer create(PaymentRequestDto paymentRequestDto) {
        // todo
        return 1;
    }

    public PaymentResponseDto read(Integer id) {
        // todo
        return new PaymentResponseDto();
    }

    public Boolean update(Integer id, PaymentRequestDto paymentRequestDto) {
        // todo
        return true;
    }

    public Boolean delete(Integer id) {
        // todo
        return true;
    }
}
