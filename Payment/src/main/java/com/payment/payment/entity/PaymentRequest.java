package com.payment.payment.entity;

import lombok.Data;

@Data
public class PaymentRequest { //used only for complete payment
    private String paymentType;
//    private String details;
    private String transactionId;
    private String otp;
}
