package com.payment.payment.factory;

import com.payment.payment.service.PaymentProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PaymentProcessorFactory {
//    static Map<String, PaymentProcessor> paymentProcessorMap = new HashMap<>();

    @Qualifier("UPI")
    @Autowired
    private PaymentProcessor upi;

    @Qualifier("COD")
    @Autowired
    private PaymentProcessor cod;

    @Qualifier("Card")
    @Autowired
    private PaymentProcessor card;

    @Qualifier("NetBanking")
    @Autowired
    private PaymentProcessor netBanking;

//    public static void put (String key, PaymentProcessor paymentProcessor) {
//        System.out.println("Registering payment processor: " + key);
//        paymentProcessorMap.put(key, paymentProcessor);
//    }

    public PaymentProcessor getPaymentProcessor (String key) {
//        return paymentProcessorMap.get(key);
        return switch (key) {
            case "UPI" -> upi;
            case "COD" -> cod;
            case "Card" -> card;
            case "NetBanking" -> netBanking;
            default -> null;
        };
    }
}
