package com.payment.payment.service;


import com.payment.payment.factory.PaymentProcessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@ComponentScan(basePackages = {"com.payment.payment.registry"})
public class PaymentHandler {
    @Autowired
    PaymentProcessorFactory registry;


    public String handlePayment (String paymentType, String details) {
        String transactionID = null;
        System.out.println(registry.getPaymentProcessor(paymentType));
        if (registry.getPaymentProcessor(paymentType) != null) {
            transactionID = registry.getPaymentProcessor(paymentType).initiatePayment(details);
            return transactionID;
        }
        return null;
    }

    public boolean completePayment (String paymentType, int otp, String transactionID) {
        return registry.getPaymentProcessor(String.valueOf(paymentType)).completePayment(otp, transactionID, paymentType);
    }
}
