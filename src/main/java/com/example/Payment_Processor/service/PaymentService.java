package com.example.Payment_Processor.service;

import com.example.Payment_Processor.model.dto.payment.PaymentRequest;
import com.example.Payment_Processor.model.dto.payment.PaymentResponse;

public interface PaymentService {
    PaymentResponse processPayment(PaymentRequest paymentRequest);
    PaymentResponse getPaymentStatus(String transactionId);
}
