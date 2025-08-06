package com.payment.payment.service;

public class CashOnDelivery implements PaymentProcessor{
    String email="example@gmail.com";
    static Cache cache =new InMemoryCache();

    @Override
    public String initiatePayment(String details) {
        if(details.equals(email)){
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
