package com.payment.payment.service;

import com.payment.payment.factory.PaymentProcessorFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("COD")
public class CashOnDelivery implements PaymentProcessor {
    String email = "example@gmail.com";

//    private final PaymentProcessorFactory registry;
    private final Cache cache;

    public CashOnDelivery ( Cache cache) {
//        PaymentProcessorFactory registry,  this.registry = registry;
        this.cache = cache;
//        this.register();
    }

    @Override
    public String initiatePayment (String details) {
        if (details.equals(email)) {
            String transactionID = Generator.generateTransactionId();
            int originalOtp = Generator.generateOTP();
            if (cache.save(transactionID, originalOtp)) {
                return transactionID + originalOtp;
            }
        }
        return null;
    }

    @Override
    public boolean completePayment (int otp, String transactionId, String paymentType) {

        if (cache.get(transactionId) == otp) {
            cache.remove(transactionId);
            return true;
        }

        if (cache.get(transactionId) == otp) {
            cache.remove(transactionId);
            return true;
        }
        return false;
    }

//    @Override
//    public void register () {
//        registry.put("COD", this);
//    }
}
