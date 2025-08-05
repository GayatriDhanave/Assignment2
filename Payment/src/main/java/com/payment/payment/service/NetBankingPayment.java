package com.payment.payment.service;

public class NetBankingPayment implements PaymentProcessor{
    static String transactionID;
    int originalOtp=5374;
    String mpin="3243";
    public NetBankingPayment(String transactionID) {
        this.transactionID = transactionID+originalOtp;
    }
    public NetBankingPayment() {
    }

    @Override
    public String initiatePayment(String details) {
        if(details.equals(mpin)){
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
