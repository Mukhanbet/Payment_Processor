package com.example.Payment_Processor.mapper.impl;

import com.example.Payment_Processor.mapper.PaymentMapper;
import com.example.Payment_Processor.model.domain.Payment;
import com.example.Payment_Processor.model.dto.payment.PaymentRequest;
import com.example.Payment_Processor.model.dto.payment.PaymentResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PaymentMapperImpl implements PaymentMapper {
    @Override
    public Payment toPayment(PaymentRequest request) {
        Payment payment = new Payment();
        payment.setTransactionId(request.getTransactionId());
        payment.setAmount(request.getAmount());
        payment.setCurrency(request.getCurrency());
        payment.setCreatedAt(LocalDateTime.now());
        return payment;
    }

    @Override
    public PaymentResponse toResponse(Payment payment) {
        PaymentResponse response = new PaymentResponse();
        response.setTransactionId(payment.getTransactionId());
        response.setAmount(payment.getAmount());
        response.setCurrency(payment.getCurrency());
        response.setCreatedAt(payment.getCreatedAt().toString());
        return response;
    }
}
