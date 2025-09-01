package com.payment.payment.service;

import com.payment.payment.factory.PaymentProcessorFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("Card")
public class CreditCardPayment implements PaymentProcessor {
    static String cardNumber = "1323354343";
//    private final PaymentProcessorFactory registry;
    private final Cache cache;

    public CreditCardPayment (Cache cache) {
//        this.registry = registry;
        this.cache = cache;
//        this.register();
    }

    @Override
    public String initiatePayment (String details) {
        if (cardNumber.equals(details)) {
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
//        registry.put("Card", this);
//    }
}
