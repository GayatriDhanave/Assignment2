package com.payment.payment.service;

import com.payment.payment.factory.PaymentProcessorFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("UPI")
public class UPIPayment implements PaymentProcessor {
    static String upiId = "asd@oksbi";

//    private final PaymentProcessorFactory registry;
    private final Cache cache;

//

    public UPIPayment (Cache cache) {
//        this.registry = registry;
        this.cache = cache;
//        this will automatically create the beans of PaymentProcessorFactory and Cache
//        this.register();
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
    public boolean completePayment (int otp, String transactionId, String paymentType) {

        if (cache.get(transactionId) == otp) {
            cache.remove(transactionId);
            return true;
        }

        return false;
    }

//    @Override
//    public void register () {
//        registry.put("UPI", this);
//    }
}
