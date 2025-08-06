package com.payment.payment.service;


import com.payment.payment.registry.PaymentProcessorRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@ComponentScan(basePackages = {"com.payment.payment.registry"})
public class PaymentHandler {
    @Autowired
    PaymentProcessorRegistry registry;


    public String handlePayment (String paymentType, String details) {
        String transactionID = null;
        System.out.println(registry.get(paymentType));
        if (registry.get(paymentType) != null) {
            transactionID = registry.get(paymentType).initiatePayment(details);
            return transactionID;
        }
        return null;
    }

    public boolean completePayment (String paymentType, int otp, String transactionID) {
        return registry.get(String.valueOf(paymentType)).completePayment(otp, transactionID, paymentType);
    }
}
