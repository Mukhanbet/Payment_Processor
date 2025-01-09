package com.example.Payment_Processor.model.dto.payment;

import lombok.Data;

@Data
public class PaymentResponse {
    private String transactionId;
    private Double amount;
    private String currency;
    private String createdAt;
}
