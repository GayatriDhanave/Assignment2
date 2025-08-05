package com.payment.payment.service;

public class CashOnDelivery implements PaymentProcessor{
    static String transactionID;
    int originalOtp=7485;
    String email="example@gmail.com";
    public CashOnDelivery(String transactionID) {
        this.transactionID = transactionID+originalOtp;
    }
    public CashOnDelivery() {
    }

    @Override
    public String initiatePayment(String details) {
        if(details.equals(email)){
            return transactionID;}
        return null;

    }

    @Override
    public boolean completePayment(int otp, String transactionId) {
        if(transactionId.equals(transactionID) && (otp==originalOtp)){
            return true;
        }
        return false;
    }
}
