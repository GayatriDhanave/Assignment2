package com.payment.payment.service;

public class UPIPayment implements PaymentProcessor{

    static String transactionID;
    static int originalOtp=6067;
    static String upiId= "asd@oksbi";
    public UPIPayment(String transactionID) {
        this.transactionID = transactionID+originalOtp;
    }
    public UPIPayment() {
    }


    @Override
    public String initiatePayment(String details) {
        if(upiId.equals(details)){

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
