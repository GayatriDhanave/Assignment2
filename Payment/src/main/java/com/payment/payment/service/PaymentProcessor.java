package com.payment.payment.service;

public interface PaymentProcessor {
//    initiate pay
//    complete payment
    String initiatePayment(String details);
    boolean completePayment(int otp, String transactionId, String paymentType);
//    void register();
}
