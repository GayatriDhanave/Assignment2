package com.payment.payment.service;

public class CreditCardPayment implements PaymentProcessor{
    static String transactionID;
    static int originalOtp=1015;
    static String cardNumber="1323354343";

    public CreditCardPayment(String transactionID) {
        this.transactionID = transactionID+originalOtp;
    }

    public CreditCardPayment() {
    }

    @Override
    public String initiatePayment(String details) {
        if(cardNumber.equals(details)){
//            transactionID=details+originalOtp;
            return transactionID;
        }
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
