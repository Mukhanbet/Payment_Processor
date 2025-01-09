package com.example.Payment_Processor.service.impl;

import com.example.Payment_Processor.exception.CustomException;
import com.example.Payment_Processor.mapper.PaymentMapper;
import com.example.Payment_Processor.model.domain.Payment;
import com.example.Payment_Processor.model.dto.payment.PaymentRequest;
import com.example.Payment_Processor.model.dto.payment.PaymentResponse;
import com.example.Payment_Processor.repository.PaymentRepository;
import com.example.Payment_Processor.service.PaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public PaymentResponse processPayment(PaymentRequest paymentRequest) {
        if (paymentRepository.findByTransactionId(paymentRequest.getTransactionId()).isPresent()) {
            log.info("Payment already exists: {}", paymentRequest.getTransactionId());
            throw new CustomException("TransactionId is already taken", HttpStatus.CONFLICT);
        }

        log.info("Processing payment for transactionId: {}", paymentRequest.getTransactionId());
        Payment payment = paymentRepository.save(paymentMapper.toPayment(paymentRequest));

        return paymentMapper.toResponse(payment);
    }

    @Override
    public PaymentResponse getPaymentStatus(String transactionId) {
        log.info("Processing checking for transactionId: {}", transactionId);

        Payment payment = paymentRepository.findByTransactionId(transactionId)
                .orElseThrow(() -> {
                    log.error("Payment not found for transactionId: {}", transactionId);
                    return new CustomException("Payment not found", HttpStatus.NOT_FOUND);
                });

        return paymentMapper.toResponse(payment);
    }
}
