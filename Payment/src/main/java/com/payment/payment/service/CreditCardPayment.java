package com.payment.payment.service;

public class CreditCardPayment implements PaymentProcessor{
    static String cardNumber="1323354343";
    static Cache cache =new InMemoryCache();

    @Override
    public String initiatePayment(String details) {
        if(cardNumber.equals(details)){
            String transactionID=Generator.generateTransactionId();
            int originalOtp=Generator.generateOTP();
            if(cache.save(transactionID, originalOtp)){
                return transactionID+originalOtp;
            }

        }
       return null;
    }

    @Override
    public boolean completePayment(int otp, String transactionId) {
        if(cache.get(transactionId)==otp){
            cache.remove(transactionId);
            return true;
        }
        return false;
    }
}
