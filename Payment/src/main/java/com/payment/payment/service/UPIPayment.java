package com.payment.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UPIPayment implements PaymentProcessor{
    static String upiId= "asd@oksbi";


    static Cache cache =new InMemoryCache();

    @Override
    public String initiatePayment(String details) {
        if(upiId.equals(details)){
            String transactionID=Generator.generateTransactionId();
            int originalOtp=Generator.generateOTP();
            if(cache.save(transactionID, originalOtp)){
                return transactionID+originalOtp;
            }
            return null;

        }
        return null;

    }

    @Override
    public boolean completePayment(int otp, String transactionId) {
            if (cache.get(transactionId) == otp) {
                cache.remove(transactionId);
                return true;
            }

        return false;
    }
}
