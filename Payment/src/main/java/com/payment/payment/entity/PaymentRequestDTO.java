package com.payment.payment.entity;

import lombok.Data;

@Data
public class PaymentRequestDTO {
    private String paymentType;
    private String paymentId;
    private String password;
    private String cvv;

}
