package com.payment.payment.service;

import com.payment.payment.entity.PaymentRequestDTO;

public interface PaymentProcessor {
//    initiate pay
//    complete payment
    String initiatePayment(PaymentRequestDTO paymentRequestDTO);
    boolean completePayment(String otp, String transactionId, String paymentType);
    void register();
}
