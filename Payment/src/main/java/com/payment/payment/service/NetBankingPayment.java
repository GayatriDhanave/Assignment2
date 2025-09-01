package com.payment.payment.service;

import com.payment.payment.factory.PaymentProcessorFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("NetBanking")
public class NetBankingPayment implements PaymentProcessor {

    static String username = "riya";
    static String password = "Riya@1234";
//    private final PaymentProcessorFactory registry;
    private final Cache cache;

    public NetBankingPayment (Cache cache) {
//        this.registry = registry;
        this.cache = cache;
//        this.register();
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
    public boolean completePayment (int otp, String transactionId, String paymentType) {

        if (cache.get(transactionId) == otp) {
            cache.remove(transactionId);
            return true;
        }

        return false;
    }

//    @Override
//    public void register () {
//        registry.put("NetBanking", this);
//    }
}
