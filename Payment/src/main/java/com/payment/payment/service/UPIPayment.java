package com.payment.payment.service;

import com.payment.payment.registry.PaymentProcessorRegistry;
import org.springframework.stereotype.Component;

@Component
public class UPIPayment implements PaymentProcessor {
    static String upiId = "asd@oksbi";

    private final PaymentProcessorRegistry registry;
    private final Cache cache;

//

    public UPIPayment (PaymentProcessorRegistry registry, Cache cache) {
        this.registry = registry;
        this.cache = cache;
//        this will automatically create the beans of PaymentProcessorRegistry and Cache
        this.register();
//        this will automatically call the register method to register the payment processor(self)
//        no need of @PostConstruct
    }

    @Override
    public String initiatePayment (String details) {
        if (upiId.equals(details)) {
            String transactionID = Generator.generateTransactionId();
            int originalOtp = Generator.generateOTP();
            if (cache.save(transactionID, originalOtp)) {
                return transactionID + originalOtp;
            }
            return null;
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
        registry.put("UPI", this);
    }
}
