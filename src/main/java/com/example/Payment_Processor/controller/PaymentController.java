package com.example.Payment_Processor.controller;

import com.example.Payment_Processor.model.dto.payment.PaymentRequest;
import com.example.Payment_Processor.model.dto.payment.PaymentResponse;
import com.example.Payment_Processor.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping(value = "/pay", produces = MediaType.APPLICATION_XML_VALUE)
    public PaymentResponse processPayment(@RequestBody PaymentRequest paymentRequest) {
        return paymentService.processPayment(paymentRequest);
    }

    @GetMapping(value = "/check", produces = MediaType.APPLICATION_XML_VALUE)
    public PaymentResponse checkPayment(@RequestParam String paymentId) {
        return paymentService.getPaymentStatus(paymentId);
    }
}
