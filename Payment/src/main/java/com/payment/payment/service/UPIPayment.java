package com.payment.payment.service;

public class UPIPayment implements PaymentProcessor{

   String transactionID;
    int originalOtp=6067;

    @Override
    public String initiatePayment(String details) {

        transactionID=details+originalOtp;
        return transactionID;
    }

    @Override
    public boolean completePayment(int otp, String transactionId) {
        if(transactionId.equals(transactionID) && (otp==originalOtp)){
            return true;
        }
        return false;
    }
}
