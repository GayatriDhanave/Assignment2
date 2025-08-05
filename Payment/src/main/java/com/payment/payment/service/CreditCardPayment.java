package com.payment.payment.service;

public class CreditCardPayment implements PaymentProcessor{
    @Override
    public String initiatePayment(String details) {
        return "";
    }

    @Override
    public boolean completePayment(int otp, String transactionId) {
        return false;
    }
}
