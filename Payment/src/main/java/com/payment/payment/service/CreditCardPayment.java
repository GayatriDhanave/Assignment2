package com.payment.payment.service;

import com.payment.payment.entity.PaymentRequestDTO;
import com.payment.payment.registry.PaymentProcessorRegistry;
import org.springframework.stereotype.Component;

@Component
public class CreditCardPayment implements PaymentProcessor {
    static String cardNumber = "1323354343";
    private final PaymentProcessorRegistry registry;
    private final Cache cache;

    public CreditCardPayment (PaymentProcessorRegistry registry, Cache cache) {
        this.registry = registry;
        this.cache = cache;
        this.register();
    }

    @Override
    public String initiatePayment (PaymentRequestDTO dto) {
        if (dto.getPaymentType().equals("Card") && dto.getPaymentId().equals(cardNumber) && dto.getCvv().equals("123")) {
            String transactionID = Generator.generateTransactionId();
            int originalOtp = Generator.generateOTP();
            if (cache.save(transactionID, String.valueOf(originalOtp))) {
                return transactionID + originalOtp;
            }
        }
        return null;
    }

    @Override
    public boolean completePayment (String otp, String transactionId, String paymentType) {

        if (cache.get(transactionId).equals(otp)) {
            cache.remove(transactionId);
            return true;
        }

        return false;
    }

    @Override
    public void register () {
        registry.put("Card", this);
    }
}
