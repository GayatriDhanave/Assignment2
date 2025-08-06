package com.payment.payment.service;

public class NetBankingPayment implements PaymentProcessor{

    static String username="riya";
    static String password="Riya@1234";
    static Cache cache =new InMemoryCache();

    @Override
    public String initiatePayment(String details) {
        if(details.equals(username)&&password.equals(password)){
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
