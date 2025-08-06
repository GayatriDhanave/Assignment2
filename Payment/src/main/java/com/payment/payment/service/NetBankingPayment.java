package com.payment.payment.service;

import com.payment.payment.registry.PaymentProcessorRegistry;
import org.springframework.stereotype.Component;

@Component
public class NetBankingPayment implements PaymentProcessor {

    static String username = "riya";
    static String password = "Riya@1234";
    private final PaymentProcessorRegistry registry;
    private final Cache cache;

    public NetBankingPayment (PaymentProcessorRegistry registry, Cache cache) {
        this.registry = registry;
        this.cache = cache;
        this.register();
    }

    @Override
    public String initiatePayment (String details) {
        String[] credentials = details.split(":");
        if (credentials[0].equals(username) && credentials[1].equals(password)) {
            String transactionID = Generator.generateTransactionId();
            int originalOtp = Generator.generateOTP();
            if (cache.save(transactionID, originalOtp)) {
                return transactionID + originalOtp;
            }
        }
        return null;

    }

    @Override
    public boolean completePayment (int otp, String transactionId) {
        if (cache.get(transactionId) == otp) {
            cache.remove(transactionId);
            return true;
        }
        return false;
    }

    @Override
    public void register () {
        registry.put("NetBanking", this);
    }
}
