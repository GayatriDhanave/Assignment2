package com.payment.payment.service;

import com.payment.payment.entity.PaymentRequestDTO;
import com.payment.payment.registry.PaymentProcessorRegistry;
import org.springframework.stereotype.Component;

@Component
public class UPIPayment implements PaymentProcessor {
    static String upiId = "asd@oksbi";
    static String originalOtp = "123456";

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
    public String initiatePayment (PaymentRequestDTO dto) {
        if (upiId.equals(dto.getPaymentId())) {
            String transactionID = Generator.generateTransactionId();
//            String originalOtp = dto.getPassword();
            if (cache.save(transactionID, originalOtp)) {
                return transactionID;
            }
            return null;
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
        registry.put("UPI", this);
    }
}
