package com.payment.payment.service;

import com.payment.payment.entity.PaymentRequestDTO;
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
    public String initiatePayment (PaymentRequestDTO dto) {
//        String[] credentials = details.split(":");
        if (dto.getPaymentType().equals("NetBanking") && dto.getPaymentId().equals(username)) {
            String transactionID = Generator.generateTransactionId();
//            int originalOtp = Generator.generateOTP();
            if (cache.save(transactionID, password)) {
                return transactionID;
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
        registry.put("NetBanking", this);
    }
}
