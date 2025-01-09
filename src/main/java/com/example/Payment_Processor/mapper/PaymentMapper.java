package com.example.Payment_Processor.mapper;

import com.example.Payment_Processor.model.domain.Payment;
import com.example.Payment_Processor.model.dto.payment.PaymentRequest;
import com.example.Payment_Processor.model.dto.payment.PaymentResponse;

public interface PaymentMapper {
    Payment toPayment(PaymentRequest request);
    PaymentResponse toResponse(Payment payment);
}
