package com.payment.payment.service;


import com.payment.payment.entity.PaymentRequestDTO;
import com.payment.payment.registry.PaymentProcessorRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@ComponentScan(basePackages = {"com.payment.payment.registry"})
public class PaymentHandler {
    @Autowired
    PaymentProcessorRegistry registry;


    public String handlePayment (PaymentRequestDTO paymentRequestDto) {
        String transactionID = null;
        System.out.println(registry.get(paymentRequestDto.getPaymentType()));
        if (registry.get(paymentRequestDto.getPaymentType()) != null) {
            transactionID = registry.get(paymentRequestDto.getPaymentType()).initiatePayment(paymentRequestDto);
            return transactionID;
        }
        return null;
    }

    public boolean completePayment (String paymentType, String otp, String transactionID) {
        return registry.get(String.valueOf(paymentType)).completePayment(otp, transactionID, paymentType);
    }
}
