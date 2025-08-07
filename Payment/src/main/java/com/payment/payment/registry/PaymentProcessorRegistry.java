package com.payment.payment.registry;

import com.payment.payment.service.PaymentProcessor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PaymentProcessorRegistry {
    static Map<String, PaymentProcessor> paymentProcessorMap = new HashMap<>();

    public void put (String key, PaymentProcessor paymentProcessor) {
        System.out.println("Registering payment processor: " + key);
        paymentProcessorMap.put(key, paymentProcessor);
    }

    public PaymentProcessor get (String key) {
        return paymentProcessorMap.get(key);
    }
}
