package com.payment.payment.entity;

import lombok.Data;

@Data
public class PaymentRequest {
    private int paymentType;
    private String details;
}
